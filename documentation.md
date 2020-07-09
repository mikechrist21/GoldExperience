# Mobile-GoldExperience


## Creative Thinking

  * Use `GoldExperience` as the main class and the main data controller
  * Use MVP architecture as its clean architecture to support clean code, with the view layer and the presenter layer merged (to simplify the project complexity)
  * Use `utils/*` as helper classes
  * Use `Place`, `User`, and `Plan` inside `data/model/` as the data's container class
  * Use template XML and abstract class for multiple usage of profile form (and prevent a lot of duplicate codes)
  * Use `res/values/strings` to store all texts instead of hard-coded those texts
  * Throw an exception whenever there is invalid data on registration or update profile form (including duplicate email addresses).
  * Auto fill email after register or logout
  * This application does not need any location permission since the application only uses location from the database, not the user's current location.


## References

Here a list of URLs that had been accessed and (maybe) give some insights to this project

  * https://android--code.blogspot.com/2015/08/android-radiogroup-set-selected-index.html
  * https://developer.android.com/guide/topics/ui/dialogs#java
  * https://developer.android.com/reference/android/content/Context.html#getString%28int%29
  * https://developer.android.com/reference/android/support/design/widget/BottomNavigationView
  * https://mkyong.com/android/android-radio-buttons-example/
  * https://stackoverflow.com/questions/14933330/datepicker-how-to-popup-datepicker-when-click-on-edittext
  * https://stackoverflow.com/questions/16076985/listview-row-buttons-how-do-i-create-a-custom-adapter-that-connects-a-view-oncl/16077840#16077840
  * https://stackoverflow.com/questions/18677421/android-relativelayout-in-scrollview
  * **UNUSED, QUIZ ONLY** https://stackoverflow.com/questions/18854060/how-to-implement-progressbar-while-loading-data/18854219#18854219
  * https://stackoverflow.com/questions/1944656/android-global-variable
  * https://stackoverflow.com/questions/24992936/how-to-check-if-a-radiobutton-is-checked-in-a-radiogroup-in-android
  * https://stackoverflow.com/questions/3438276/how-to-change-the-text-on-the-action-bar
  * https://stackoverflow.com/questions/3676095/relativelayout-height-to-fill-remaining-space/30149147
  * https://stackoverflow.com/questions/5271387/get-color-int-from-color-resource/32253801#32253801
  * https://www.journaldev.com/17899/java-simpledateformat-java-date-format
  * https://stackoverflow.com/questions/18097748/how-to-get-row-count-in-sqlite-using-android/18098603#18098603
  * https://stackoverflow.com/questions/11776356/android-sqlite-contraint-conflict-try-catch-couldnt-catch/12725468#12725468
  * https://developer.android.com/training/data-storage/sqlite#PersistingDbConnection


## Useful Information

(Maybe) Unrelated, but could be related in the future

  * http://www.programmersought.com/article/1187676681/


## Image Resources

  * https://www.flaticon.com/free-icon/g-logo-circle_7719
  * https://www.flaticon.com/free-icon/contract_2285296
  * https://www.flaticon.com/free-icon/user_1077063


## Device Specifications

Here a list of devices used in testing phase

  * API 28 Android 9: Asus ZenFone Max Pro M1
  * API 24 Android 7.0: Nexus 5X (*Emulator*)
  * API 24 Android 7.0: Pixel 2 (*Emulator*)
  * API 23 Android 6.0: Nexus S (*Emulator*)
  * API 27 Android 8.1.0: Pixel 2 (*Emulator*)
  * API 23 Android 6.0: phone
