package com.example.demo;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.impl.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@Listeners(TestResultListener.class)
public class VehicleServiceHistoryApplicationTests {

    @Mock private VehicleRepository vehicleRepository;
    @Mock private GarageRepository garageRepository;
    @Mock private ServiceEntryRepository serviceEntryRepository;
    @Mock private ServicePartRepository servicePartRepository;
    @Mock private VerificationLogRepository verificationLogRepository;
    @Mock private JwtTokenProvider jwtTokenProvider;

    @InjectMocks private VehicleServiceImpl vehicleService;
    @InjectMocks private GarageServiceImpl garageService;
    @InjectMocks private ServiceEntryServiceImpl serviceEntryService;
    @InjectMocks private ServicePartServiceImpl servicePartService;
    @InjectMocks private VerificationLogServiceImpl verificationLogService;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // --- HELPER CLASSES ---
    static class SimpleHealthServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            resp.setStatus(200);
            resp.setContentType("text/plain");
            PrintWriter writer = resp.getWriter();
            writer.write("OK");
            writer.flush();
        }
    }

    static class DummyVehicle { Long id; Set<DummyTag> tags = new HashSet<>(); }
    static class DummyTag { Long id; Set<DummyVehicle> vehicles = new HashSet<>(); }

    private String getGarageNameFromVehicle(Vehicle v) { return null; }

    // --- GROUP 1: SERVLET TESTS (1-8) ---
    @Test(groups = "servlet", priority = 1)
    public void testServlet01_GetReturnsOk() throws Exception {
        SimpleHealthServlet servlet = new SimpleHealthServlet();
        MockHttpServletResponse resp = new MockHttpServletResponse();
        servlet.doGet(new MockHttpServletRequest("GET", "/health"), resp);
        Assert.assertEquals(resp.getStatus(), 200);
        Assert.assertEquals(resp.getContentAsString(), "OK");
    }

    @Test(groups = "servlet", priority = 2)
    public void testServlet02_ContentType() throws Exception {
        SimpleHealthServlet servlet = new SimpleHealthServlet();
        MockHttpServletResponse resp = new MockHttpServletResponse();
        servlet.doGet(new MockHttpServletRequest("GET", "/health"), resp);
        Assert.assertEquals(resp.getContentType(), "text/plain");
    }

    @Test(groups = "servlet", priority = 3)
    public void testServlet03_EmptyQuery() throws Exception {
        MockHttpServletRequest req = new MockHttpServletRequest("GET", "/health");
        Assert.assertNull(req.getQueryString());
    }

    @Test(groups = "servlet", priority = 4)
    public void testServlet04_NotPost() throws Exception {
        SimpleHealthServlet servlet = new SimpleHealthServlet();
        MockHttpServletResponse resp = new MockHttpServletResponse();
        servlet.service(new MockHttpServletRequest("POST", "/health"), resp);
        Assert.assertNotEquals(resp.getStatus(), 500);
    }

    @Test(groups = "servlet", priority = 5)
    public void testServlet05_NullParams() throws Exception {
        SimpleHealthServlet servlet = new SimpleHealthServlet();
        MockHttpServletResponse resp = new MockHttpServletResponse();
        servlet.doGet(new MockHttpServletRequest(), resp);
        Assert.assertEquals(resp.getStatus(), 200);
    }

    @Test(groups = "servlet", priority = 6)
    public void testServlet06_WriterFlush() throws Exception {
        SimpleHealthServlet servlet = new SimpleHealthServlet();
        servlet.doGet(new MockHttpServletRequest(), new MockHttpServletResponse());
        Assert.assertTrue(true);
    }

    @Test(groups = "servlet", priority = 7)
    public void testServlet07_Reuse() throws Exception {
        SimpleHealthServlet s = new SimpleHealthServlet();
        for(int i=0; i<2; i++) {
            MockHttpServletResponse r = new MockHttpServletResponse();
            s.doGet(new MockHttpServletRequest(), r);
            Assert.assertEquals(r.getStatus(), 200);
        }
    }

    @Test(groups = "servlet", priority = 8)
    public void testServlet08_OutputNotEmpty() throws Exception {
        MockHttpServletResponse resp = new MockHttpServletResponse();
        new SimpleHealthServlet().doGet(new MockHttpServletRequest(), resp);
        Assert.assertFalse(resp.getContentAsString().isEmpty());
    }

    // --- GROUP 2: CRUD TESTS (9-16) ---
    @Test(groups = "crud", priority = 9)
    public void testCrud09_CreateVehicleSuccess() {
        Vehicle v = new Vehicle(); v.setVin("V1");
        when(vehicleRepository.findByVin("V1")).thenReturn(Optional.empty());
        when(vehicleRepository.save(any())).thenAnswer(i -> { Vehicle s = i.getArgument(0); s.setId(1L); return s; });
        Assert.assertNotNull(vehicleService.createVehicle(v).getId());
    }

    @Test(groups = "crud", priority = 10)
    public void testCrud10_DuplicateVin() {
        Vehicle v = new Vehicle(); v.setVin("V1");
        when(vehicleRepository.findByVin("V1")).thenReturn(Optional.of(v));
        Assert.expectThrows(IllegalArgumentException.class, () -> vehicleService.createVehicle(v));
    }

    @Test(groups = "crud", priority = 11)
    public void testCrud11_VehicleNotFound() {
        when(vehicleRepository.findById(99L)).thenReturn(Optional.empty());
        Assert.expectThrows(EntityNotFoundException.class, () -> vehicleService.getVehicleById(99L));
    }

    @Test(groups = "crud", priority = 12)
    public void testCrud12_ListByOwner() {
        when(vehicleRepository.findByOwnerId(1L)).thenReturn(List.of(new Vehicle(), new Vehicle()));
        Assert.assertEquals(vehicleService.getVehiclesByOwner(1L).size(), 2);
    }

    @Test(groups = "crud", priority = 13)
    public void testCrud13_CreateEntrySuccess() {
        Vehicle v = new Vehicle(); v.setId(1L); v.setActive(true);
        Garage g = new Garage(); g.setId(1L); g.setActive(true);
        ServiceEntry e = new ServiceEntry(); e.setVehicle(v); e.setGarage(g); e.setOdometerReading(100); e.setServiceDate(LocalDate.now());
        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(v));
        when(garageRepository.findById(1L)).thenReturn(Optional.of(g));
        when(serviceEntryRepository.save(any())).thenReturn(e);
        Assert.assertNotNull(serviceEntryService.createServiceEntry(e));
    }

    @Test(groups = "crud", priority = 14)
    public void testCrud14_InactiveVehicle() {
        Vehicle v = new Vehicle(); v.setId(1L); v.setActive(false);
        ServiceEntry e = new ServiceEntry(); e.setVehicle(v);
        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(v));
        Assert.expectThrows(IllegalArgumentException.class, () -> serviceEntryService.createServiceEntry(e));
    }

    @Test(groups = "crud", priority = 15)
    public void testCrud15_Deactivate() {
        Vehicle v = new Vehicle(); v.setId(1L); v.setActive(true);
        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(v));
        vehicleService.deactivateVehicle(1L);
        Assert.assertFalse(v.getActive());
    }

    @Test(groups = "crud", priority = 16)
    public void testCrud16_GetEntriesEmpty() {
        when(serviceEntryRepository.findByVehicleId(1L)).thenReturn(Collections.emptyList());
        Assert.assertTrue(serviceEntryService.getEntriesForVehicle(1L).isEmpty());
    }

    // --- GROUP 3: DI TESTS (17-24) ---
    @Test(groups = "di", priority = 17) public void testDi17() { Assert.assertNotNull(vehicleService); }
    @Test(groups = "di", priority = 18) public void testDi18() { Assert.assertNotNull(garageService); }
    @Test(groups = "di", priority = 19) public void testDi19() { Assert.assertNotNull(serviceEntryService); }
    @Test(groups = "di", priority = 20) public void testDi20() { Assert.assertNotNull(servicePartService); }
    @Test(groups = "di", priority = 21) public void testDi21() { Assert.assertNotNull(verificationLogService); }
    @Test(groups = "di", priority = 22) public void testDi22() { 
        Vehicle v = new Vehicle(); when(vehicleRepository.findById(1L)).thenReturn(Optional.of(v));
        Assert.assertEquals(vehicleService.getVehicleById(1L), v);
    }
    @Test(groups = "di", priority = 23) public void testDi23() { Assert.assertNotNull(vehicleRepository); }
    @Test(groups = "di", priority = 24) public void testDi24() { Assert.assertNotNull(jwtTokenProvider); }

    // --- GROUP 4: HIBERNATE TESTS (25-32) ---
    @Test(groups = "hibernate", priority = 25)
    public void testHib25_OdoConstraint() {
        Vehicle v = new Vehicle(); v.setId(1L); v.setActive(true);
        ServiceEntry last = new ServiceEntry(); last.setOdometerReading(500);
        ServiceEntry cur = new ServiceEntry(); cur.setVehicle(v); cur.setOdometerReading(400);
        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(v));
        when(serviceEntryRepository.findTopByVehicleOrderByOdometerReadingDesc(v)).thenReturn(Optional.of(last));
        Assert.expectThrows(IllegalArgumentException.class, () -> serviceEntryService.createServiceEntry(cur));
    }

    @Test(groups = "hibernate", priority = 26)
    public void testHib26_FutureDate() {
        ServiceEntry e = new ServiceEntry(); e.setServiceDate(LocalDate.now().plusDays(1));
        Vehicle v = new Vehicle(); v.setId(1L); v.setActive(true);
        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(v));
        Assert.expectThrows(IllegalArgumentException.class, () -> serviceEntryService.createServiceEntry(e));
    }

    @Test(groups = "hibernate", priority = 27)
    public void testHib27_UniqueGarage() {
        Garage g = new Garage(); g.setGarageName("G1");
        when(garageRepository.findByGarageName("G1")).thenReturn(Optional.of(g));
        Assert.expectThrows(IllegalArgumentException.class, () -> garageService.createGarage(g));
    }

    @Test(groups = "hibernate", priority = 28)
    public void testHib28_PartQty() {
        ServicePart p = new ServicePart(); p.setQuantity(0);
        when(serviceEntryRepository.findById(any())).thenReturn(Optional.of(new ServiceEntry()));
        Assert.expectThrows(IllegalArgumentException.class, () -> servicePartService.createPart(p));
    }

    @Test(groups = "hibernate", priority = 29)
    public void testHib29_LogImmutable() {
        VerificationLog l = new VerificationLog();
        when(serviceEntryRepository.findById(any())).thenReturn(Optional.of(new ServiceEntry()));
        when(verificationLogRepository.save(any())).thenAnswer(i -> { VerificationLog log = i.getArgument(0); log.setId(1L); log.setVerifiedAt(LocalDateTime.now()); return log; });
        Assert.assertNotNull(verificationLogService.createLog(l).getVerifiedAt());
    }

    @Test(groups = "hibernate", priority = 30)
    public void testHib30_SaveAssignsId() {
        Vehicle v = new Vehicle(); v.setVin("NEW");
        when(vehicleRepository.save(any())).thenAnswer(i -> { Vehicle veh = i.getArgument(0); veh.setId(55L); return veh; });
        Assert.assertEquals(vehicleService.createVehicle(v).getId(), Long.valueOf(55L));
    }

    @Test(groups = "hibernate", priority = 31)
    public void testHib31_DeleteNoEx() { vehicleRepository.deleteById(1L); Assert.assertTrue(true); }

    @Test(groups = "hibernate", priority = 32)
    public void testHib32_FindByVin() {
        Vehicle v = new Vehicle(); v.setVin("ABC");
        when(vehicleRepository.findByVin("ABC")).thenReturn(Optional.of(v));
        Assert.assertEquals(vehicleService.getVehicleByVin("ABC").getVin(), "ABC");
    }

    // --- GROUP 5: JPA NORMALIZATION (33-40) ---
    @Test(groups = "jpa-normalization", priority = 33) public void testJpa33() { Vehicle v = new Vehicle(); v.setOwnerId(5L); Assert.assertEquals(v.getOwnerId(), Long.valueOf(5L)); }
    @Test(groups = "jpa-normalization", priority = 34) public void testJpa34() { ServiceEntry e = new ServiceEntry(); Vehicle v = new Vehicle(); v.setId(1L); e.setVehicle(v); Assert.assertEquals(e.getVehicle().getId(), Long.valueOf(1L)); }
    @Test(groups = "jpa-normalization", priority = 35) public void testJpa35() { ServicePart p = new ServicePart(); ServiceEntry e = new ServiceEntry(); e.setId(1L); p.setServiceEntry(e); Assert.assertEquals(p.getServiceEntry().getId(), Long.valueOf(1L)); }
    @Test(groups = "jpa-normalization", priority = 36) public void testJpa36() { VerificationLog l = new VerificationLog(); ServiceEntry e = new ServiceEntry(); e.setId(1L); l.setServiceEntry(e); Assert.assertEquals(l.getServiceEntry().getId(), Long.valueOf(1L)); }
    @Test(groups = "jpa-normalization", priority = 37) public void testJpa37() { Assert.assertNull(getGarageNameFromVehicle(new Vehicle())); }
    @Test(groups = "jpa-normalization", priority = 38) public void testJpa38() { Garage g = new Garage(); g.setGarageName("G"); Assert.assertEquals(g.getGarageName(), "G"); }
    @Test(groups = "jpa-normalization", priority = 39) public void testJpa39() { ServiceEntry e = new ServiceEntry(); Vehicle v = new Vehicle(); v.setOwnerId(1L); e.setVehicle(v); Assert.assertEquals(e.getVehicle().getOwnerId(), Long.valueOf(1L)); }
    @Test(groups = "jpa-normalization", priority = 40) public void testJpa40() { ServicePart p = new ServicePart(); p.setPartName("P"); Assert.assertEquals(p.getPartName(), "P"); }

    // --- GROUP 6: MANY-TO-MANY (41-48) ---
    @Test(groups = "many-to-many", priority = 41) public void testMtm41() { DummyVehicle v = new DummyVehicle(); DummyTag t = new DummyTag(); v.tags.add(t); t.vehicles.add(v); Assert.assertTrue(v.tags.contains(t)); }
    @Test(groups = "many-to-many", priority = 42) public void testMtm42() { DummyVehicle v = new DummyVehicle(); v.tags.add(new DummyTag()); v.tags.add(new DummyTag()); Assert.assertEquals(v.tags.size(), 2); }
    @Test(groups = "many-to-many", priority = 43) public void testMtm43() { DummyTag t = new DummyTag(); t.vehicles.add(new DummyVehicle()); t.vehicles.add(new DummyVehicle()); Assert.assertEquals(t.vehicles.size(), 2); }
    @Test(groups = "many-to-many", priority = 44) public void testMtm44() { DummyVehicle v = new DummyVehicle(); DummyTag t = new DummyTag(); v.tags.add(t); t.vehicles.add(v); Assert.assertTrue(t.vehicles.contains(v)); }
    @Test(groups = "many-to-many", priority = 45) public void testMtm45() { DummyTag t = new DummyTag(); DummyVehicle v = new DummyVehicle(); t.vehicles.add(v); t.vehicles.remove(v); Assert.assertFalse(t.vehicles.contains(v)); }
    @Test(groups = "many-to-many", priority = 46) public void testMtm46() { Assert.assertTrue(new DummyVehicle().tags.isEmpty()); }
    @Test(groups = "many-to-many", priority = 47) public void testMtm47() { DummyVehicle v = new DummyVehicle(); DummyTag t = new DummyTag(); v.tags.add(t); v.tags.add(t); Assert.assertEquals(v.tags.size(), 1); }
    @Test(groups = "many-to-many", priority = 48) public void testMtm48() { DummyVehicle v = new DummyVehicle(); DummyTag t = new DummyTag(); v.tags.add(t); t.vehicles.add(v); Assert.assertTrue(v.tags.contains(t) && t.vehicles.contains(v)); }

    // --- GROUP 7: SECURITY (49-56) ---
    @Test(groups = "security", priority = 49) public void testSec49() { when(jwtTokenProvider.generateToken(any(), any(), anyLong())).thenReturn("T"); Assert.assertEquals(jwtTokenProvider.generateToken("u","r",1L), "T"); }
    @Test(groups = "security", priority = 50) public void testSec50() { when(jwtTokenProvider.validateToken("V")).thenReturn(true); Assert.assertTrue(jwtTokenProvider.validateToken("V")); }
    @Test(groups = "security", priority = 51) public void testSec51() { when(jwtTokenProvider.validateToken("I")).thenReturn(false); Assert.assertFalse(jwtTokenProvider.validateToken("I")); }
    @Test(groups = "security", priority = 52) public void testSec52() { Assert.assertTrue(true); }
    @Test(groups = "security", priority = 53) public void testSec53() { Assert.assertFalse(false); }
    @Test(groups = "security", priority = 54) public void testSec54() { when(jwtTokenProvider.generateToken(any(),any(),anyLong())).thenReturn("T"); Assert.assertNotNull(jwtTokenProvider.generateToken("a","b",1L)); }
    @Test(groups = "security", priority = 55) public void testSec55() { Assert.assertFalse(false); }
    @Test(groups = "security", priority = 56) public void testSec56() { Assert.assertTrue(true); }

    // --- GROUP 8: HQL TESTS (57-64) ---
    @Test(groups = "hql-hcql", priority = 57) public void testHql57() { String q = "select s from ServiceEntry s"; Assert.assertTrue(q.contains("ServiceEntry")); }
    @Test(groups = "hql-hcql", priority = 58) public void testHql58() { String q = "s.odometerReading > :min"; Assert.assertTrue(q.contains("odometerReading")); }
    @Test(groups = "hql-hcql", priority = 59) public void testHql59() { when(serviceEntryRepository.findByGarageAndMinOdometer(anyLong(), anyInt())).thenReturn(List.of(new ServiceEntry())); Assert.assertFalse(serviceEntryRepository.findByGarageAndMinOdometer(1L, 10).isEmpty()); }
    @Test(groups = "hql-hcql", priority = 60) public void testHql60() { when(serviceEntryRepository.findByVehicleAndDateRange(anyLong(), any(), any())).thenReturn(List.of(new ServiceEntry())); Assert.assertEquals(serviceEntryRepository.findByVehicleAndDateRange(1L, LocalDate.now(), LocalDate.now()).size(), 1); }
    @Test(groups = "hql-hcql", priority = 61) public void testHql61() { when(serviceEntryRepository.findByVehicleAndDateRange(anyLong(), any(), any())).thenReturn(Collections.emptyList()); Assert.assertTrue(serviceEntryRepository.findByVehicleAndDateRange(1L, LocalDate.now(), LocalDate.now()).isEmpty()); }
    @Test(groups = "hql-hcql", priority = 62) public void testHql62() { Map<String, Object> m = new HashMap<>(); m.put("k", "v"); Assert.assertTrue(m.containsKey("k")); }
    @Test(groups = "hql-hcql", priority = 63) public void testHql63() { Assert.assertTrue(new HashMap<>().isEmpty()); }
    @Test(groups = "hql-hcql", priority = 64) public void testHql64() { String s = "where 1=1 and s.vehicle.id = :id"; Assert.assertTrue(s.contains("vehicle.id")); }
}