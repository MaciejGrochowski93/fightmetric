# fightmetric

This is a spring boot, rest application which combines two things - reading MMA odds from bookmaker's api
(www.pinnacle.com), and scrapping data about incoming fights from the official UFC's site (www.ufcstats.com).

Starting the application takes some time, as the initial method downloads all the required data. This solution allows
user to use the endpoints conveniently, and quickly.

/odds - displays UFC matchups, and odds available at pinnacle.

/fights - statistics about all the incoming fights, scrapped from UFC's official site. Afterwards, the read
data is being processed, and mapped into Fighters with calculated Advantages.

/finals - combination of the two above - Finals contain Fighters names, odds, and all the Advantages.

Pinnacle api got a limited amount of requests per month - if it gets exceeded, you can switch the
feign.client.config.default.defaultRequestHeaders.X-RapidAPI-Key
in the application.properties.
