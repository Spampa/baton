# Baton

![Java](https://img.shields.io/badge/Java-21-ED8B00?logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?logo=apachemaven&logoColor=white)
![SQLite](https://img.shields.io/badge/SQLite-003B57?logo=sqlite&logoColor=white)
![JavaFX](https://img.shields.io/badge/JavaFX-proposed-5382A1?logo=openjdk&logoColor=white)
![JUnit](https://img.shields.io/badge/JUnit-5-25A162?logo=junit5&logoColor=white)
![Eclipse](https://img.shields.io/badge/Eclipse-2C2255?logo=eclipseide&logoColor=white)
![StarUML](https://img.shields.io/badge/StarUML-UML-4A4A4A)
![License: MIT](https://img.shields.io/badge/License-MIT-blue)

Baton is a desktop application for building block-based automations without writing code. Inspired by [n8n](https://n8n.io/) and [Node-RED](https://nodered.org/), it lets you connect blocks visually into a flow chart and run it on your own computer. It is aimed at users with basic computer skills who want to automate repetitive tasks.

Baton is all-in-one: it needs no external server and stores projects in a local embedded database. An internet connection is required only by blocks that call external services.

> **Status:** early development. The features below come from the [requirements specification](docs/03_srs.md) and are not all implemented yet.

## How it works

1. Create a project and add a flow chart to it.
2. Add blocks to the canvas and set their parameters. A parameter is either a constant value or the output of another block.
3. Connect output ports to input ports. Baton accepts a connection only if the two ports are compatible.
4. Start the flow chart from a trigger. The `baton-engine` runs the blocks in sequence, following the connections.

If a block fails, the engine stops the automation and shows which block failed and why. The application does not crash.

## Planned features

Features are prioritized with the MoSCoW method.

**Must have**

- Visual editor for adding, connecting and configuring blocks
- Validation of connections between block ports
- Persistent storage of flow charts, with multiple projects
- Execution engine with error reporting
- Core blocks: manual trigger, file reading (including CSV), text transformation, calculation, condition (`if`) and file writing

**Should have**

- HTTP GET, scheduled trigger and delay blocks
- Project export and import, to move projects between devices
- `for` and `while` loops
- Blueprint-style grid background

**Could have**

- File metadata, new-file trigger, ChatGPT, Telegram trigger and terminal command blocks
- Animated baton that moves between blocks to show the running block
- Multiple branches from one output, always run serially

**Out of scope**

- Parallel execution
- Multi-user support
- Blocks that require OAuth login on other platforms

## Platforms

Baton targets Windows, macOS and Debian, and requires Java 21.

## Documentation

- [Project plan](docs/01_project_plan.md)
- [Project management](docs/02_project_management.md)
- [Software requirements specification](docs/03_srs.md)

The documents are written in Italian.

## Team

Nicolò Spampatti, Marco Magri, Giovanni Ghilardi and Matteo Piffari.

Developed for the Software Engineering course at the University of Bergamo.

## License

Released under the [MIT License](LICENSE.md).
