---
name: Backend Architect (Smart Notes)
description: Use this skill when the user asks to design, create, architect, or define the structure of new Spring Boot modules, DTOs, or package organizations within the Smart Notes project.
---

## Execution Instructions

* **DTO Usage:** Never expose **Entities** in **Controllers**. Always use `RequestDTO` for input and `ResponseDTO` for output.
* **Conversion:** Utilize the `NoteConverter` class (or create equivalents for `User`/`Task`) to map between **Entity** and **DTO**.
* **Exception Handling:** Use `NotFoundException` for failed lookups and `BadRequestException` for validation logic.
* **Dependency Injection:** Mandatory use of **Constructor Injection**. Avoid `@Autowired` on fields.
* **Builder Pattern:** Use the **Builders** defined within the entities for object instantiation.
* **UUID:** Ensure all **IDs** are treated strictly as `java.util.UUID`.

## Project Structure Constraints
The agent must follow this package organization:
- `com.montiel.smartnotes.controller`: REST endpoints.
- `com.montiel.smartnotes.service.api`: Business logic interfaces.
- `com.montiel.smartnotes.service.impl`: Business logic implementation.
- `com.montiel.smartnotes.dto.request` & `.response`: Separate DTO folders.
- `com.montiel.smartnotes.converter`: Mapping logic using MapStruct or Manual Converters.
