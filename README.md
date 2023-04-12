# Java app with JavaFX + Google API

This app should compile and run in codespaces.

## JavaFX

1. Start up codespaces.
2. Eventually once it starts up, codespaces should open another tab with a remote desktop (you may have to enable popups). _(If it doesn't open in a new window, you can open one yourself by clicking on the PORTS tab and using the "Open in Browswer" button, under "Local Address".)
3. Click the `Connect` button, and log into the remote desktop with password `abc123`.
4. Back in code spaces, run the following commands in the terminal:
```
cd my-dsl
gradle run
```
5. The JavaFX window should pop up in the remote desktop. Close the window to quit the application.

## Google API
- You will need to add credentials to `my-dsl/app/src/main/resources/credentials.json`, following the instructions from the [Java QuickStart](https://developers.google.com/sheets/api/quickstart/java).
- The code will compile in codespaces, but anything that uses Google API won't run properly because authentication won't work from codespaces. (You should be able to run it locally, though.)

## How it works
The key parts are:
- The [`.devcontainer`](./.devcontainer) folder, which sets up the container than can display GUIs, plus the remote desktop.
- The Gradle project format, created by running the `gradle init` command in the `my-dsl` folder, choosing a Desktop Java app and the remaining default options.
- The [`my-dsl/app/build.gradle`](./my-dsl/app/build.gradle) file, which configures the dependencies for JavaFX and the Google API.
