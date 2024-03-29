# Indiana Squares and The Thingamajig

This is a project I did for a uni class a while back, I intend to work on it a bit and maybe expand on the idea (sometime).

### prerequisites for building or running the game

You must have Java 8 or later installed. If you're not sure if it's installed on your system check out the below links which can help you find out:

- Linux: https://phoenixnap.com/kb/check-java-version-linux
- Windows 10: https://www.howtogeek.com/717330/how-to-check-your-java-version-on-windows-10/
- Windows 11: https://www.howtogeek.com/838703/how-to-check-your-java-version-on-windows-11/

### build

Maven support has been added, so it's suggested to simply execute `mvn package` from the project root directory, which builds the project and creates a `.jar` file in the `target` folder. Otherwise, it really doesn't contain many files and no external dependencies, so you can import it into your IDE of choice and build it there. Please note that the `data` folder contains the levels, sounds, etc., and it's supposed to be in the project root directory.

### run

Go to the [Releases](https://github.com/slendersnax/indiana-sqaures-and-the-thingamajig/releases) page, download the latest `.zip` file, extract the contents somewhere, then simply double click on the extracted `indiana-squares-and-the-thingamajig-x.x.jar` file, which is an executable.