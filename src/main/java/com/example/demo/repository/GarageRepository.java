
GarageRepository extends JpaRepository<Garage, Long> {
 Optional<Garage> findByGarageName(String garageName);
}

ServiceEntryRepository extends JpaRepository<ServiceEntry, Long> {
 ServiceEntry findTopByVehicleOrderByOdometerReadingDesc(Vehicle vehicle);
 List<ServiceEntry> findByVehicleId(Long vehicleId);
}

ServicePartRepository extends JpaRepository<ServicePart, Long> {
 List<ServicePart> findByServiceEntryId(Long entryId);
}

VerificationLogRepository extends JpaRepository<VerificationLog, Long> {
 List<VerificationLog> findByServiceEntryId(Long entryId);
}

UserRepository extends JpaRepository<User, Long> {
 Optional<User> findByEmail(String email);
}