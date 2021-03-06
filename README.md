### CHALLENGE
Post-Covid scenario:
People are now free to travel everywhere but because of the pandemic, a lot of hotels went bankrupt. Some former famous travel places are left with only one hotel.
You’ve been given the responsibility to develop a booking API for the very last hotel in Cancun.
The requirements are:
- API will be maintained by the hotel’s IT department.
- As it’s the very last hotel, the quality of service must be 99.99 to 100% => no downtime
- For the purpose of the test, we assume the hotel has only one room available
- To give a chance to everyone to book the room, the stay can’t be longer than 3 days and can’t be reserved more than 30 days in advance.
- All reservations start at least the next day of booking,
- To simplify the use case, a “DAY’ in the hotel room starts from 00:00 to 23:59:59.
- Every end-user can check the room availability, place a reservation, cancel it or modify it.
- To simplify the API is insecure.

### STACK USED

The API is created using:
- Java 11
- Spring v2.5.4 with starter-pack, H2, dev-tools and more
- Lombok v1.18.20
- MapStruct 1.4.2.Final

### ENDPOINTS

- Server port used: 8080.
- Example of payload used for testing:
{
	"id": 1,
	"start_date": "2021-09-26",
	"end_date": "2021-09-28"
}

| Path | Description |
| ------------ | ------------ |
| /api/v1/booking/create | Place a reservation |
| /api/v1/booking/find | Check room availability |
| /api/v1/booking/update | Modify a reservation |
| /api/v1/booking/cancel/1 | Cancel a reservation |

### FINAL THOUGHTS

- Although mentioned that the API is insecure, I used the DTO pattern (request/response) to solve part of the problem.
- As the API will be maintained by the hotel’s IT department and thinking in a solution decoupled as possible, I used a Facade pattern that could be used to do some complex tasks before calling the Booking Service methods. I thought in a second solution using application events, but since the API is simple, I decided to not focus much on that.
- The MapStruct was used to help simplify the process of mappings within the project and make the code as clean as possible.
- Since there is only one room available, I chose to use the ID = 1 for all reservations within the project (as seen in the payload example above).
