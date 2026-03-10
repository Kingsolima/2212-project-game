Staying Alive - High-Level Architecture
=======================================

This document summarizes the initial folder and package structure for the **Staying Alive** typing game, based on the planning and design documentation.

## Top-Level Folders

- `docs/` – course planning/design docs and additional technical docs.
- `assets/` – art, audio, and other game assets.
- `data/` – JSON data files (`players.json`, `master.json`, etc.).
- `lib/` – third-party libraries (e.g., Jackson, TinySound).
- `src/main/java/` – main Java source code.
- `src/test/java/` – unit tests.

## Java Package Layout

Base package:

- `ca.uwo.cs2212.group54.stayingalive`
  - Application entry point and navigation controller live here.

Subpackages:

- `ca.uwo.cs2212.group54.stayingalive.ui`
  - Swing screens and dialogs (main menu, login modal, tutorial, player screen, gameplay screen, stats, store, parental controls, high scores).

- `ca.uwo.cs2212.group54.stayingalive.game`
  - Core gameplay logic and level data (game controller, level data, enemies, player character, game state).

- `ca.uwo.cs2212.group54.stayingalive.accounts`
  - Account, statistics, progress tracking, and parental control back-end logic.

- `ca.uwo.cs2212.group54.stayingalive.sprites`
  - Sprite interface and concrete sprite types for player, enemies, powerups, and cosmetics.

- `ca.uwo.cs2212.group54.stayingalive.powerups`
  - Powerup models, types, and inventories.

- `ca.uwo.cs2212.group54.stayingalive.cosmetics`
  - Cosmetic models, types, and inventories.

- `ca.uwo.cs2212.group54.stayingalive.stats`
  - Typing statistics calculations and high score tables.

- `ca.uwo.cs2212.group54.stayingalive.storage`
  - JSON file access for `players.json`, `master.json`, and any other saved data, using Jackson.

- `ca.uwo.cs2212.group54.stayingalive.audio`
  - Audio manager and enums for music and sound effects (TinySound).

- `ca.uwo.cs2212.group54.stayingalive.util`
  - Shared utilities (validation, Swing helpers, etc.).

Implementation details for each package will be added in future iterations. For now, the structure provides clear separation of concerns aligned with the project documentation.
