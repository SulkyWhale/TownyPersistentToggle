# TownyPersistentToggle

TownyPersistentToggle is a Minecraft plugin built on the Paper API that preserves the state of Towny resident modes and HUDs across player logins and server restarts.

This plugin supports all resident modes (accessed via `/res toggle [mode]`), Towny's map HUD (`/towny map hud`) and Towny's plot permission HUD (`/plot perm hud`).

*Note: This plugin is developed and tested against the latest version of Paper. While it may work on Paper forks or older versions, they are not actively tested or supported.*

### Dependencies

 - [Towny](https://github.com/TownyAdvanced/Towny)

### Installation

1. Download the latest JAR from [releases](https://github.com/SulkyWhale/TownyPersistentToggle/releases) and place it in your server's plugins folder.
2. Restart your server.

### Building

If you wish to build the plugin yourself, follow the instructions below.

1. Clone the repository:
    ```shell
    git clone https://github.com/SulkyWhale/TownyPersistentToggle.git
    ```
2. Change into the working directory:
    ```shell
    cd TownyPersistentToggle
    ```
3. Build the JAR:
    ```shell
    mvn clean package
    ```
   
### Issues

If you encounter any bugs or would like a new feature added, please open an [issue](https://github.com/SulkyWhale/TownyPersistentToggle/issues/new). Be sure to check existing issues first to avoid duplicates.

### Contributing

Contributions are welcome. If you have any bug fixes, improvements, or new features you would like to add to this project, feel free to open a [pull request](https://github.com/SulkyWhale/TownyPersistentToggle/pulls).

### License

TownyPersistentToggle is licensed under the GNU GPL v3. Please see the [license](/LICENSE) for more information.
