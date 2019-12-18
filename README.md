# intellij-license-api-plugin

## About 
An intellij plugin. This plugin adds a new top-level menu to IntelliJ IDEA. The Menu has one sub-menu that, when clicked brings up a dialog box. Selecting a license from the visible dropdown will render a set of details about that license. Connects to the free license API and displays license details as a dialog (with tooltips).

*NOTE*: First launch of the menu is slow since it connects to the license API to retrieve all license information. Ensure an internet connection and be patient. The dialog will eventually display.

## Plugin Develpment Language
The current lingua franca for plugins is Kotlin, the prior was Java. This plugin is written with Java. It is extremely simple to convert a Java project into Kotlin using IntelliJ IDEA (for anyone interested in checking out language differences).

## API
The API used in this plugin is thanks to https://github.com/cmccandless/license-api/blob/master/README.md. Sincere thanks to @cmccandless for the contribution. Following the licensing model of the API, this plugin too is released under an MIT license.

## Reason
The plugin was built as an initial sample to test plugin development. This plugin is not a standard for best practices for plugin development, nor is it thoughtful in arcitecture and design. Hopefully, it helps some other developer looking for tips on how to get started with plugins.
