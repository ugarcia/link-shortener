LINK-SHORTEN - Linux x64 systems
---------------------------------

1. Dependencies:
----------------

- Java JRE must be installed on the system.
- Java executable should be included in system PATH variable.
*NOTE: Usually OpenJDK comes preinstalled with linux distros so no need to do anything.

2. Start:
---------

- Extract TAR to a folder with user read/write access privileges.
  * Command line: tar xfzv link-shorten_linux_x64.tar.gz

- Inside the extracted folder, run start.sh

3. Functionality:
-----------------

- Open a browser and navigate to http://localhost:8080 for link shortener UI.
- Enter original link into text field and press 'Shorten' button.
- Copy shortened link and paste into another browser tab, it should redirect to original link.

4. Shutdown:
------------

- CTRL-C or close the server terminal window.
