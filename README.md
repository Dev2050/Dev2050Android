# Dev2050Android
AndroidProjects

#Instructions for MacOS and Linux users:

For MacOS users please use the following links and follow the instructions to setup the environment.

	a)Android Developers : https://developer.android.com/studio/install.html from here select Mac.
	b)Relevant Site: http://www.makeuseof.com/tag/run-android-apps-mac-os/.

For Linux users please use the link: https://www.howtoforge.com/tutorial/how-to-install-and-run-android-apk-on-linux-with-shashlik/
  and follow the instructions to setup the environment.
 
	a)Android Developers : https://developer.android.com/studio/install.html from here select linux.
	b)Relevant Site: https://www.howtoforge.com/tutorial/how-to-install-and-run-android-apk-on-linux-with-shashlik/ ,[->b]
	Please Note: site "b" above is for Ubuntu users.

For All usersGenereal Instruction on how to Set up your development environment for all operating system users can be found at the link: https://developer.android.com/topic/instant-apps/getting-started/setup.html.

During installation, One can simply use the Android Package Kit (APK) which is the package file format used by the Android operating system for distribution and installation of mobile apps and middleware. 

	Please use this link to build the app from the command Line: https://developer.android.com/studio/build/building-cmdline.html.

Another way is to use debug release apk file by installing it on your android mobile phone from"...\OpeningHours\app\build\outputs\apk" or you can do the same using the following instruction:

Build a debug APK:

	For immediate app testing and debugging, you can build a debug APK. The debug APK is signed with a debug key provided 
	by the SDK tools and allows debugging through adb.

	To build a debug APK, open a command line and navigate to the root of your project directory—from 
	Android Studio, select View > Tool Windows > Terminal. To initiate a debug build, invoke the assembleDebug task:

	Excute this command onthe terminal: gradlew assembleDebug

	This creates an APK named module_name-debug.apk in project_name/module_name/build/outputs/apk/. 
	The file is already signed with the debug key and aligned with zipalign, so you can immediately install it on a device. 

	
