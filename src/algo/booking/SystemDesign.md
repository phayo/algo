# System Design Prep

## Case 1
Booking.com offers a feature “book now, pay later” that allows users to make a
booking a hotel room now and pay later, on or before check-in. Credit card details
are provided by users at the time of booking. However, people can misuse this
feature & make bookings with fraudulent credit cards which can cause legal problems.
Design a system that detects the use of fraudulent credit cards and blocks them from
booking rooms. Given: a 3rd party API that provides a list of newly added fraudulent
credit card numbers.

I would propose the following solution for the system design:

Questions I would ask (including expected answers!):
Q:How frequently is the list in Fred updated?
A: As soon as a fraud detection system detects a card is fraud, it updates in Fred. We do not have more data.

Q:What kind of API does Fred have? Does it always give a huge list?
A:Yes, Fred is an external system and it only returns a list. We cannot make changes to that system. CC fraud can be real-time. Ideally, there are ML models to detect fraud. Fred is the system that is managing the list of fraud CCs.

Can Fred publish a message when something is added or removed from their list?NO
Does Fred have an API to query if a card is in the list? NO

Q: How critical is consistency? i.e. if a new card has been added to Fred’s list, is it required that we are not allowed to use that card immediately, or is some time-gap allowed?
A: Ideally, it is ok to have a gap. Let’s say max 1 day.

Q: What about a scenario that a booking was made using a card that was not fraudulent. But the card was later identified as a fraud. What do we do with open bookings on that card?
A: We have to identify those bookings and flag them.

scale questions:
How big is the list of CC? 1 million
How many bookings per day? 1 million
How does Fred let us know the CC information? Ideally, CC numbers would not be passed around.. They could be hashed / encrypted?

Scenario 1: Case where the booking was made on a valid card, but the card was later identified as a fraud.

A small note on credit-cards. Usually, we do not pass credit-card numbers around. We usually store tokens, and the actual credit-card information is stored in a separate ultra-secure storage.

Solution: we have a log of bookings being made, with booking_id, date, token. We could have a map-reduce job, which consumes the logs, and produces a map where the key is the token, and the value is a sorted list of booking/date combinations (sorted on date). This job runs daily, and keeps updating a key-value database, where key is the cc token, and the value is a list of txns made by that CC.

We also keep a cache to maintain the list of fraud cards. Key is CC token, and value is date added. The writes are once a day. We do not expect this to scale. we can use a cache, because this is derived data, and the true source is Fred. assume 1 million keys, and 100 bytes for each entry, gives us 100mb of storage for the cache.

Then we run another daily job where we get the list of fraud cards, and first check in the cache if the card already exists. For every new card,
we make an entry in the cache.
We check in our persisten datastore for the existence of this card. We can mark all bookings for a given card as invalid.

Further, to maintain the size of our persistent datastore, any booking older than the current date can be removed.

What about the cards that are not fraud anymore? How wil they be removed from our cache? This can be done using our solution. We can have a date-updated. If a card is not present in Fred’s list on a particular day, it’s date-udpated will become old. it can be removed from the cache.. i.e. it can be evicted.

Scenario 2 -
We have the cache of the fraud cards, that is refreshed daily. For every booking, we can easily check against the cache if the card exists (and if it’s actually updated recently). If it is found, we reject the transaction instantaneously.
 
The solution does not make a distinction between the 2 modes of payment, because I did not see a reason. (Could be a trick requirement?) We are being given the CC details during the confirmation of the booking. In terms of identifying fraud, it does not matter if the payment is being taken at that point by booking.com, or charged by the merchant later. We can always charge the card instantly, and later reject the booking and make a refund to that card in case it is fraud. The design stays the same in my opinion. Opinions are welcome!

## Case 2
This was a System Design Round.
You're given an external system which can tell you whether there are available rooms at a hotel. Multiple hotel booking services like Booking, Expedia, etc are all using this service to book hotels. How would you handle the problem of making sure a hotel room is booked.
There was a lot of discussion about handling different type of cases, how the hotel booking services will update the external service when they've booked a room, collision cases, and how to make this design extensible.

## Case 3
Design a view counter service which shows the number of current viewers viewing the product page on Booking.com.
I explained them the components and how to scale the service using Redis and queues
I realised I stammered a lot throughout the interview due to lack of confidence, which confused the interviewers totally.

## Case 4
System design: Design a scalable system to create a feature where a user looking at a property on booking.com can see how many other users are also looking at the same property.

## Case 5
The next round was system design round. Below question was posted to me by two engineers.
Booking.com has close to 1M booking per day. When user makes a reservation in a hotel, he chooses a room and confirms his reservation and they get the CC details. 
The Payment can happen in two ways, Either Booking.com charges the payment or pass the CC details to hotel partener and let the partner capture the transaction when user uses the hotel. 
Consider that we have a external system called fred, which gets us the list of fraudulent CC's. Design a system to identify if a reservation was made using a fraudulent CC considerting both the mode of payments
