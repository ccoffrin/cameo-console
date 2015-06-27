#Instructions for setting up the Cameo environment

## Overview ##
Several technologies are required to begin working on Cameo.  The minimum set of applications required to develop Cameo is as follows:
  * [JDK 1.5](http://java.sun.com/javase/downloads/index.jsp) - Java Runtime / Compiler
  * [subversion](http://subversion.tigris.org/) - version control system
  * [Eclipse](http://www.eclipse.org/) - IDE
  * [subclipse](http://subclipse.tigris.org/) - Eclipse Subversion Integration
  * [ANLTR Eclipse](http://antlreclipse.sourceforge.net/) - Eclipse ANTLR Integration
  * [AJDT](http://www.eclipse.org/ajdt/) - Eclipse AspectJ Integration

All of the above tools are free and work seemlessly across platforms.

## Java 1.5 ##
Make sure you have a Java 1.5 compatable JDK installed.  You can test which version of java you have by typing

`java -version`

at the command line.  If you do not have java installed or need to update the JDK you can download it here:

[JDK 1.5](http://java.sun.com/javase/downloads/index.jsp)

## Subversion ##
The Cameo code is stored with the Subversion revision system.

[Subversion Home Page](http://subversion.tigris.org/)

You will need to install a Subversion client and request access to the code repository before you can develop Cameo.  Which Subversion client you need depends on your operating system:

  * **WinXP** - [TortoiseSVN](http://tortoisesvn.net/) provides an interface that is integrated with the Explorer.
  * **Mac OS X** - we recommend the following pre-build binary package: [Martin Ott's packaging](http://www.codingmonkeys.de/mbo/articles/2006/11/08/subversion-1-4-2). Grab the package and install it. It contains the command line interface to svn (so that you can use svn directly from the terminal as for a traditional UNIX)
  * **Linux** - simply grab a recent SVN client for your distribution (any version above 1.4.0 should do).

### Requesting Access ###
Sign up as a developer at http://code.google.com/p/cameo-console/.

### Testing ###
Once you have a user name and login set up, you can test your connection to the SVN server with the following command:

`svn list https://cameo-console.googlecode.com/svn/trunk/`

If this command returns a list of directories, you have installed subversion successfully.


## Eclipse ##
Download and install the Eclipse IDE.

[Eclipse Home Page](http://www.eclipse.org/)

Eclipse version 3.2 is required, if you have version 3.1 please upgrade to 3.2.  If you need to use Eclipse 3.1, two versions of Eclipse can be used on the same computer as long as they reside in separate installation directories and the workspaces they use are disjoint.

If you are unfamiliar with Eclipse you may find the following video demonstrations helpful.  [Introduction To Eclipse](http://showmedo.com/videos/series?name=IntroductionToEclipseWithJava_JohnM)

#### Installing an Eclipse Plugin ####
The Subversion, ANTLR, and AJDT tools can be installed through Eclipe's built in plugin manager.

To install a new plugin nagivate to Help > Software Updates > Find and Install.  Then select "Search New Features to Install" and in the next dialog box click on the "New Remote Site..." button.

You will be presented with a dialog box where you should give a name and the URL of the site where the Eclipse extension can be found. For instance, for AJDT, you would enter the information as shown below
  * Name - AJDT
  * URL - http://www.eclipse.org/ajdt/

Finally, select that site (check its box) and click finish. Follow the straightforward directions, read and accept the license agreement and restart Eclipse when complete.

### ANTLR in Eclipse ###
The ANTLR plugin, [ANTLR Eclipse](http://antlreclipse.sourceforge.net/).  If you install this plugin and primarily use the Eclipse IDE for Cameo development you should never have to think about ANTLR again.  All necessary generated code with be managed for you.

### AJDT ###
Grab the update site URL from the [AJDT Download page](http://www.eclipse.org/ajdt/downloads/).   As long as AJDT is installed before you check out the Cameo project you should have no problems compiling the code.

### Subclipse Plugin ###
Although svn has a command line interface it is easiest to work with Subversion Eclipse integration.  Our recommened Subversion plugin is [Subclipse](http://subclipse.tigris.org/).  Subclipse can be installed easily from the Eclipse Update site provided through their website: http://subclipse.tigris.org/update_1.0.x.

**Note:** If you are using Eclipse 3.2 and the latest version of Subclipse with OS X you may need to change the Eclipse SVN Interface to JavaSVN.  This can be done from Window > Prefrences > Team > SVN.


## Checking-out the source code ##
The subversion repository for Cameo is hosted on cameo-console.googlecode.com.  To check out the source code in the repository, follow these directions.

Begin by starting the SVN Checkout wizard, Select, File > New > Other.  A window of wizards should pop up.  Navigate in the dialog box down to the SVN folder (which should be visible since you installed Subclipse) and pick **Checkout Projects from SVN**

In the next dialog box, enter an SVN URL for the root of the repository as follows,

  * Pick "Create a New repository location"
  * Enter the URL https://cameo-console.googlecode.com/svn/.

If everything goes according to plan, you will be prompted for your password, or even better, subversion will used a cached copy of your password. The outcome should be a dialog box populated with all the _projects_ that exist in the repository.

The next step is **critical**. To check out a project you must select the correct sub-directory. Each subversion project uses the following structure: The root of the project has 3 sub-directories named trunk, tags and branches.
  * trunk - contains the current working revision
  * tags - contain one subdirectory whose name is a symbolic tag
  * branches - plays the same role for experimental (i.e., disruptive) changes that should not be on the trunk

Currely Cameo is the only project hosted at this location.  To check out the projecet simply select the folder named `trunk` before you click on "next". Once you click on "next", Eclipse will retrieve information about the Eclipse project that exists underneath that directory on the server. If all is working correctly, it should retrieve the project we created and display the project name (Cameo in this case).

At this point, simply clicking on "Finish" will complete the process and initiate the actual Subversion transaction to download the entire content of the project. Depending on your connection speed, this should take a minute or two.