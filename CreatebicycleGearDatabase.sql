DROP DATABASE IF EXISTS bicycleGearDatabase;
CREATE DATABASE bicycleGearDatabase;


USE bicycleGearDatabase;

CREATE TABLE gearRatio
(
gearRatioID		    INT 	 PRIMARY KEY,
gearRatioDescription TEXT(500)  
);

CREATE TABLE gearPairings
(
gearParingsID		    INT 	 PRIMARY KEY,
gearPairingsDescription TEXT(500)  
);

INSERT INTO gearRatio 
VALUES (1,'A 2.3 gear ratio is a very easy gear ratio, this ratio would be used for extreme mountain bike climbs or on a casual beach cruiser bike'),
(2,'A 2.4 gear ratio is a very easy gear ratio, this ratio would be used for extreme mountain bike climbs or on a casual beach cruiser bike. It would privide slighlty more resistance then a 2.3 gear ratio.'),
(3,'A 2.5 gear ratio is a very easy gear ratio, this ratio would be used for extreme mountain bike climbs or on a casual beach cruiser bike. It would privide slighlty more resistance then a 2.4 gear ratio.'),
(4,'A 2.6 gear ratio is a easier gear ratio, this ratio would be used for trail riding on mountain bikes or on a casual beach cruiser bike.'),
(5,'A 2.7 gear ratio is a easier gear ratio, this ratio would be used for trail riding on mountain bikes or on a casual beach cruiser bike. It would privide slighlty more resistance then a 2.5 gear ratio.'),
(6,'A 2.8 gear ratio is moderate gear ratio, this ratio is usually set up on most fixed gear road bikes. It also a good for novice mountain bikers on non hilly courses.'),
(7,'A 2.9 gear ratio is moderate gear ratio, this ratio would be a slight upgrade for a fixed gear road bikes. It also a good for intermediate mountain bikers on non hilly courses.'),
(8,'A 3.0 gear ratio is moderate gear ratio, this ratio would be a slight upgrade for a fixed gear road bikes. It also a good for intermediate mountain bikers on non hilly courses.'),
(9,'A 3.1 gear ratio is a moderate-slighlty tough gear ratio, as a moderate upgrade for fixed gear cyclists. It would also be a ratio that was stock on a track bike.'),
(10,'A 3.2 gear ratio is a moderate-slighlty tough gear ratio, as a moderate upgrade for fixed gear cyclists. It would also be a ratio that was stock on a track bike. It would be privide slighly more resistance than a 3.1 gear ratio'),
(11,'A 3.3 gear ratio is a moderate-slighlty tough gear ratio. It would be a good ratio for a intermediate/advanced road cyclist that is training.'),
(12,'A 3.4 gear ratio is a tough gear ratio. It would be a good ratio for a advanced road cyclist that is training.'),
(13,'A 3.5 gear ratio is tougher advanced gear ratio, this ratio would be used for competing track cyclist or advanced road cyclists training.'),
(14,'A 3.6 gear ratio is tough advanced gear ratio, this ratio would be used for competing track cyclist'),
(15,'A 3.7 gear ratio is a tough elite gear ratio, this ratio would be used olympic level track cyclists'),
(16,'A 3.8 gear ratio is a tough elite gear ratio, this ratio would be used olympic level track cyclists'),
(17,'A 3.9 gear ratio is a tough elite gear ratio, this ratio would be used olympic level track cyclists'),
(18,'A 4.0 gear ratio is a tough elite gear ratio, this ratio would be used olympic level track cyclists'),
(19,'A 4.1 gear ratio is a super-tough advanced gear ratio, it would be used in extreme cycling events'),
(20,'A 4.2 gear ratio is a super-tough advanced gear ratio, it would be used in extreme cycling events'),
(21,'A 4.3 gear ratio is a super-tough advanced gear ratio, it would be used in extreme cycling events');

 INSERT INTO gearPairings 
 /*Commuting*/
VALUES (1,'For a begginer rider commuting a 44/17 gear pairing would provide enough speed to get from one place to another without the high stress/taxing of a workout. For something a little eaiser a 44/18 would provide that.'),
 ( 2,'For a novide rider commuting a 44/17 gear pairing would provide enough speed to get from one place to another without the high stress/taxing of a workout. For something a little more challenging a 44/16 would provide that.'),
 (3,'For a intermediate rider commuting a 44/16 gear pairing would provide enough speed to get from one place to another without the high stress/taxing of a workout. For something a little more challenging a 44/15 would provide that.'),
 (4,'For a advanced rider commuting a 48/18 gear pairing would provide enough gear with someone who is an avid rider without putting a training amount of stress on the body. For something a little more challenging with some more speed a 48/17 would provide that.'),
 (5,'For a begginer rider commuting on a all-purpose trail a 44/18 gear pairing would provide enough crusing speed with a slightly easier gear for the frequent stopping of all-purpose trail. For something a little eaiser a 44/19 would provide that but cause a lower top speed.'),
 (6,'For a novice rider commuting on a all-purpose trail a 44/18 gear pairing would provide enough crusing speed with a slightly easier gear for the frequent stopping of all-purpose trail. For something a little eaiser a 44/19 would provide that but cause a lower top speed.'),
 (7,'For a intermediate rider commuting on a all-purpose trail a 44/17 gear pairing would provide enough crusing speed with a slightly easier gear for the frequent stopping of all-purpose trail. For something a little eaiser a 44/18 would provide that.'),
 (8,'For a advanced rider commuting on a all-purpose trail a 46/17 gear pairing provide enough gear with someone who is an avid rider with a lower gear ratio for the frequent stopping of all-purpose trail. For something a little eaiser a 46/18 would provide that. For a 48 tooth chainring a 48/18 would work closer to the 46/17 gear pairing.'),
 (9,'For a begginer rider commuting with some hills a 44/18 gear pairing would provide a good cruising speed to get from one place to another with a smaller gear for the hills . For something a little eaiser a 44/19 would provide that but may not provide enough gear going down the hills.'),
 (10,'For a novice rider commuting with some hills a 44/18 gear pairing would provide a good cruising speed to get from one place to another with a smaller gear for the hills . For something a little eaiser a 44/19 would provide that but may not provide enough gear going down the hills.'),
 (11,'For a intermediate rider commuting with some hills a 44/17 gear pairing would provide a good cruising speed to get from one place to another with a smaller gear for the hills . For something a little eaiser a 44/16 would provide.'),
 (12,'For a advanced rider commuting with some hills a 46/17 gear pairing would provide enough gear for a regular cyclist to get from one place to another with a slighlty smaller gear ratio for the hills . For something a little eaiser a 46/18 would provide that.For a 48 tooth chainring a 48/19 would work closer to the 46/17 gear pairing.'),
 (13,'For a begginer rider commuting with many hills a 44/19 gear pairing would provide a good cruising speed for a beginner to get from one place to another with a smaller gear for the hills, this smaller gear will be tough for going down the hills though.'),
 (14,'For a novice rider commuting with many hills a 44/19 gear pairing would provide a good cruising speed for a novice rider to get from one place to another with a smaller gear for the hills, this smaller gear will be tough for going down the hills though.'),
 (15,'For a intermediate rider commuting with many hills a 44/18 gear pairing would provide a decent cruising speed to get from one place to another with a smaller gear for the hills.'),
 (16,'For a advanced rider commuting with many hills a 44/16 gear pairing would provide enough gear for a regular cyclist to get from one place to another with a slighlty smaller gear ratio for the hills . For something a little eaiser a 44/17 would provide that.For a 48 tooth chainring a 48/18 would work closer to the 44/16 gear pairing.'),
 (17,'For a begginer rider commuting through a off-road trail 44/18 gear pairing would provide a good cruising speed for a beginner with a slightly easier gear for the technical aspect a offroad trail can have, For something a little eaiser a 44/19 would provide that but cause a lower top speed..'),
 (18,'For a novice rider commuting on a off-road trail a 44/18 gear pairing would provide enough crusing speed with a slightly easier gear for for the technical aspect a offroad trail can have. For something a little eaiser a 44/19 would provide that but cause a lower top speed.'),
 (19,'For a intermediate rider commuting on a off-road trail a 44/17 gear pairing would provide enough crusing speed with a slightly easier gear for the technical aspect a offroad trail can have. For something a little eaiser a 44/18 would provide that.'),
 (20,'For a advanced rider commuting on a off-road trail a 46/17 gear pairing provide enough gear with someone who is an avid rider with a lower gear ratio for the technical aspect a offroad trail can have. For something a little eaiser a 46/18 would provide that. For a 48 tooth chainring a 48/18 would work closer to the 44/16 gear pairing.'),
 /*Leisure Ride*/
 (21,'For a leisure ride for a begginer rider a 44/17 gear pairing would provide a comfortable ride without the high stress/taxing of a workout. For something a little eaiser a 44/18 would provide that.'),
 (22,'For a leisure ride for a novice rider a 44/17 gear pairing would provide a comfortable ride without the high stress/taxing of a workout. For something a little eaiser a 44/18 would provide that.'),
 (23,'For a leisure ride for intermediate rider a 44/16 gear pairing would provide a comfortable ride without the high stress/taxing of a workout. For something a little more challenging a 44/15 would provide that.'),
 (24,'For a leisure ride for a advanced rider a 48/18 gear pairing would provide a comfortable ride without putting a training amount of stress on the body. For something a little more challenging with some more speed a 48/17 would provide that.'),
 (25,'For a beginner out for a leisure ride on a all-purpose trail a 44/18 gear pairing would provide enough a comfortable ride with a slightly easier gear for the frequent stopping of all-purpose trail. A 44/19 would provide that a easier ride.'),
 (26,'For a novice out for a leisure ride on a all-purpose trail a 44/18 gear pairing would provide enough a comfortable ride with a slightly easier gear for the frequent stopping of all-purpose trail. A 44/19 would provide that a easier ride.'),
 (27,'For a intermediate rider out for a leisure ride on a all-purpose trail a 44/17 gear pairing would provide comfortable ride with a slightly easier gear for the frequent stopping of all-purpose trail. For something a little eaiser a 44/18 would provide that.'),
 (28,'For a advanced rider out for a leisure ride on a all-purpose trail a 46/17 gear pairing provide relaxing ride with lower gear ratio for the frequent stopping of all-purpose trail. For something a little eaiser a 46/18 would provide that. For a 48 tooth chainring a 48/18 would work closer to the 46/17 gear pairing.'),
 (29,'For a begginer rider on a leisure ride with some hills a 44/18 gear pairing would provide a comfortable ride with a smaller gear for the hills . For something a little eaiser a 44/19 would provide that but may not provide enough gear going down the hills.'),
 (30,'For a novice rider on a leisure ride with some hills a 44/18 gear pairing would provide a comfortable ride with a smaller gear for the hills . For something a little eaiser a 44/19 would provide that but may not provide enough gear going down the hills.'),
 (31,'For a intermediate rider on a leisure ride with some hills a 44/17 gear pairing would provide a comfortable ride with a smaller gear for the hills . For something a little eaiser a 44/16 would provide.'),
 (32,'For a advanced rider on a leisure ride with some hills a 46/17 gear pairing would provide a relaxing ride with a slighlty smaller gear ratio for the hills . For something a little eaiser a 46/18 would provide that.For a 48 tooth chainring a 48/19 would work closer to the 46/17 gear pairing.'),
 (33,'For a begginer rider on a leisure ride with many hills a 44/19 gear pairing would provide as comfortable a ride with a bunch of hills could provide, with a smaller gear for the hills, this smaller gear will be tough for going down the hills though.'),
 (34,'For a novice rider on a leisure ride with many hills a 44/19 gear pairing would provide as comfortable a ride with a bunch of hills could provide, with a smaller gear for the hills, this smaller gear will be tough for going down the hills though.'),
 (35,'For a intermediate rider on a leisure ride with many hills a 44/18 gear pairing would provide as comfortable a ride with a bunch of hills could provide, with a smaller gear for the hills.'),
 (36,'For a advanced rider on a leisure ride with many hills a 44/16 gear pairing would provide a relaxing ride with a slighlty smaller gear ratio for the hills . For something a little eaiser a 44/17 would provide that.For a 48 tooth chainring a 48/18 would work closer to the 44/16 gear pairing.'),
 (37,'For a begginer rider on a off-road leisure ride a 44/18 gear pairing would provide a comfortable ride with a slightly easier gear for the technical aspect a offroad trail can have, For something a little eaiser a 44/19 would provide that but cause a lower top speed.'),
 (38,'For a novice rider on a off-road leisure ride a 44/18 gear pairing would provide a comfortable ride with a slightly easier gear for the technical aspect a offroad trail can have, For something a little eaiser a 44/19 would provide that but cause a lower top speed.'),
 (39,'For a intermediate rider on a off-road leisure ride a 44/17 gear pairing would provide a comfortable ride with a slightly easier gear for the technical aspect a offroad trail can have. For something a little eaiser a 44/18 would provide that.'),
 (40,'For a advanced rider on a off-road leisure ride a 46/17 gear pairing provide a relaxing ride with a lower gear ratio for the technical aspect a offroad trail can have. For something a little eaiser a 46/18 would provide that. For a 48 tooth chainring a 48/18 would work closer to the 44/16 gear pairing.'),
 /*Training*/
 (41,'For a begginer rider training a 44/16 gear pairing would provide a good base for training. For something a little eaiser a 44/17 would provide that.'),
 (42,'For a novice rider training a 44/16 gear pairing would provide a good base for training. For something a little tougher a 44/15 would provide that.'),
 (43,'For a intermediate rider training a 48/18 gear pairing would provide a good gear setup for training. For something a little more challenging a 48/17 would provide that.'),
 (44,'For a advanced rider training a 48/16 gear pairing would provide a good gear ratio for a training session. For something a little more challenging with some more speed a 48/15 would provide that.'),
 (45,'Training on a all-purpose trail is not suggested.'),
 (46,'Training on a all-purpose trail is not suggested.'),
 (47,'Training on a all-purpose trail is not suggested.'),
 (48,'Training on a all-purpose trail is not suggested.'),
 (49,'For a begginer rider on a training ride with some hills a 44/17 gear pairing would provide a good base for some light hill training . For something a little eaiser a 44/18 would provide that but may not provide enough gear going down the hills.'),
 (50,'For a novice rider training with some hills a 44/17 gear pairing would provide a good base for training. For something a little tougher a 44/16 would provide that.'),
 (51,'For a intermediate rider training with some hills a 48/18 gear pairing would provide a good training base for the hills . For something a more challenging a 48/17 would provide that.'),
 (52,'For a advanced rider training with some hills a 48/17 gear pairing would provide a good gear setup for a training session with some hills.For a more challenging gear setup a 48/16 would work'),
 (53,'For a begginer rider on a training ride with many hills a 44/18 gear pairing would provide a good gear base for a hill workout, this smaller gear will be tough for going down the hills though. A 44/17 would be a provide a tougher ride but be easier going down the hills.'),
 (54,'For a novice rider on a training ride with many hills a 44/18 gear pairing would provide a good gear base for a hill workout, this smaller gear will be tough for going down the hills though. A 44/17 would be a provide a tougher ride but be easier going down the hills.'),
 (55,'For a intermediate rider training with many hills a 48/19 gear pairing would provide a good gear setup for hill training. A 48/18 gear setup would provide a more challenging ride.'),
 (56,'For a advanced rider on a training ride with many hills a 48/18 gear pairing would provide a good gear setup for hill training. For something a more challenging a 48/17 would provide that.'),
 (57,'For a begginer rider on a off-road training ride a 44/17 gear pairing would provide a good base for training with a slightly easier gear for the technical aspect a offroad trail can have, For something a little eaiser a 44/18 would provide that but cause a lower top speed.'),
 (58,'For a novice rider on a off-road training ride a 44/17 gear pairing would provide a good base for training with a slightly easier gear for the technical aspect a offroad trail can have, For something more challenging a 44/16 gear pairing would provide that.'),
 (59,'For a intermediate rider on a off-road training ride a 46/18 gear pairing would provide a good base for training. For something a more challenging a 46/17 would provide that.'),
 (60,'For a advanced rider on a off-road training ride a 46/15 gear pairing provide a good base for training. For something a little eaiser a 46/17 would provide that. '),
 /*TrailRiding*/
 (61,'For a begginer rider trail riding a 44/18 gear pairing would provide a good base for a trail ride. For something a little eaiser a 44/19 would provide that.'),
 (62,'For a novice rider trail riding a 44/18 gear pairing would provide a good base for a training. For something a little tougher a 44/17 would provide that.'),
 (63,'For a intermediate rider trail riding a 48/19 gear pairing would provide a good base for a trail ride. For something a little more challenging a 48/18 would provide that.'),
 (64,'For a advanced rider trail riding a 48/18 gear pairing would provide a good gear ratio for a trail ride. For something a little more challenging with some more speed a 48/17 would provide that.'),
 (65,'Not Applicable.'),
 (66,'Not Applicable.'),
 (67,'Not Applicable.'),
 (68,'Not Applicable.'),
 (69,'For a begginer rider trail riding with some hills a 44/18 gear pairing would provide a good base. For something a little eaiser a 44/19 would provide that but may not provide enough gear going down the hills.'),
 (70,'For a novice rider trailgearPairingsDescription riding with some hills a 44/18 gear pairing would provide a good base. For something a little tougher a 44/17 would provide that.'),
 (71,'For a intermediate rider training with some hills a 44/17 gear pairing would provide a good base. For something a more challenging a 44/16 would provide that.'),
 (72,'For a advanced rider trail riding with some hills a 44/16 gear pairing would provide a good base.For a more challenging gear setup a 46/16 would work'),
 (73,'For a begginer rider trail riding with many hills a 44/19 gear pairing would provide a good gear base for trying to ride the hills of a trail.'),
 (74,'For a novice rider trail riding with many hills a 44/18 gear pairing would provide a good gear base for hills on the trail. A 44/17 would be a provide a tougher ride.'),
 (75,'For a intermediate rider trail riding with many hills a 44/17 gear pairing would provide a good gear setup for hills on the trail . A 44/16 gear setup would provide a more challenging ride.'),
 (76,'For a advanced rider on a training ride with many hills a 44/16 gear pairing would provide a good gear setup for hills on a trail. For something a more challenging a 46/16 would provide that.'),
 (77,'For a begginer rider on a technical off-road training ride a 44/17 gear pairing would provide a good base for the technical aspect a offroad trail can have, For something a little eaiser a 44/18 would provide that.'),
 (78,'For a novice rider on a technical training ride a 44/17 gear pairing would provide a good base for the technical aspect a offroad trail can have, For something more challenging a 44/16 gear pairing would provide that.'),
 (79,'For a intermediate rider on a technical off-road training ride a 46/18 gear pairing would provide a good base for a technical trail. For something a more challenging a 46/17 would provide that.'),
 (80,'For a advanced rider on a technical off-road training ride a 46/15 gear pairing provide a good base for a technical trail. For something a little eaiser a 46/17 would provide that. ');
 
