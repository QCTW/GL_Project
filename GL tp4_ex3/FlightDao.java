public interface FlightDao{
	
	Flight getFlightbyId(String commercialId);
	List<Flight> getFlightsbyAirport(String airport);
	List<Flight> getFlightsbyDepartureAirport(String airport);
	List<Flight> getFlightsbyArrivalAirport(String airport);

}