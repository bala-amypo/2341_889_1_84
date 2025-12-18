package com.example.demo.service;

import com.example.demo.model.ServiceEntry;
import java.util.List;

public interface ServiceEntryService {

    ServiceEntry createServiceEntry(ServiceEntry entry);
    ServiceEntry getServiceEntryById(Long id);
    List<ServiceEntry> getEntriesForVehicle(Long vehicleId);
    List<ServiceEntry> getEntriesByGarage(Long garageId);
}

package com.example.demo.service;

import com.example.demo.model.ServicePart;
import java.util.List;

public interface ServicePartService {

    ServicePart createPart(ServicePart part);
    ServicePart getPartById(Long id);
    List<ServicePart> getPartsForEntry(Long entryId);
}

package com.example.demo.service;

import com.example.demo.model.VerificationLog;
import java.util.List;

public interface VerificationLogService {

    VerificationLog createLog(VerificationLog log);
    VerificationLog getLogById(Long id);
    List<VerificationLog> getLogsForEntry(Long entryId);
}

package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {

    User register(User user);
    User getUserByEmail(String email);
}