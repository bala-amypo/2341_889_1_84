VehicleRepository extends JpaRepository<Vehicle, Long> {
 Optional<Vehicle> findByVin(String vin);
 List<Vehicle> findByOwnerId(Long ownerId);
}
