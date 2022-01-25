# OneRepMaxTracker
Extract data from file, saves it in Room DB and shows data based on the ORM based on the Brzycki Formula for each exercise.
In the detail of each oneRepMax exercise, shows a graph with all the ORM history of records.

![Screen Recording 2022-01-25 at 18 04 00](https://user-images.githubusercontent.com/45268196/151024598-5d313df8-26a6-45cf-a24c-f638f8178ae5.gif)

## Clean Architecture based on : 
- **View**
- **ViewModel**
- **useCase**
- **Repository**
- **DataSources**


## Main libraries used
- [Kotlin](https://kotlinlang.org/docs/reference/)
- [Koin](https://github.com/InsertKoinIO/koin) (DI)
- [MVVM](https://developer.android.com/jetpack/docs/guide) (Architecture)
- [Room](https://developer.android.com/topic/libraries/architecture/room) (Persistence)
- [Coroutines](https://developer.android.com/kotlin/coroutines)
- [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart)

- [Espresso](https://developer.android.com/training/testing/espresso)
- [Mockito](https://site.mockito.org/)

# Modules Architecture 
## App
App contains Application and knows all the other modules, it is in charge of initialising Koin.
No other module knows App

## buildSrc
Everything related to Dependencies and Kotlin DSL implementation

## core-base
- **context** > Context Extensions 
- **navigation** > This module contains everything related to know about navigation, any feature module doesn't have to know about other features.
- **test** > Everything related to common test functions, rules or shared elements for Testing purposes.
- **ui** > Everything related to common-ui resources, extensions and reusable views that can be used along the project
- **domain-data-layer** > Every common class from these layers that is used or needed in any of the 2 different features

## features
- **detailGreatest1RM** > Related to get the one rep max record for each different exercise
- **detailGreatest1RM** > Everything related to the detail and record line chart of one rep max exercise

## libraries
- **oneRepMax** > Used to calculate the ORM based on Brzycki Formula

## persitence
- **asset** > Everything related to the file extraction data
- **database-room** > Everything related to Room, to have it well abstracted

----

## MUST TODO IN FUTURE
- Clean and unify the gradle.kts files
- Add more tests
