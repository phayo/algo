# System Design Prep

## Case 1
Booking.com offers a feature “book now, pay later” that allows users to make a
booking a hotel room now and pay later, on or before check-in. Credit card details
are provided by users at the time of booking. However, people can misuse this
feature & make bookings with fraudulent credit cards which can cause legal problems.
Design a system that detects the use of fraudulent credit cards and blocks them from
booking rooms. Given: a 3rd party API that provides a list of newly added fraudulent
credit card numbers.

## Case 2
This was a System Design Round.
You're given an external system which can tell you whether there are available rooms at a hotel. Multiple hotel booking services like Booking, Expedia, etc are all using this service to book hotels. How would you handle the problem of making sure a hotel room is booked.
There was a lot of discussion about handling different type of cases, how the hotel booking services will update the external service when they've booked a room, collision cases, and how to make this design extensible.

## Case 3
Design a view counter service which shows the number of current viewers viewing the product page on Booking.com.
I explained them the components and how to scale the service using Redis and queues
I realised I stammered a lot throughout the interview due to lack of confidence, which confused the interviewers totally.
