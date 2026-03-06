---
name: Backend Component Generator
description: Procedural guide to implement Services and Controllers following the NoteServiceImpl pattern.
---

## Service Implementation Protocol (The "NoteService" Pattern)

When generating a `ServiceImpl`, follow these exact steps in order:

1. **Private Final Dependencies:**
    - Define all required Repositories as `private final`.
    - Ensure `UserRepository` is always included if the entity belongs to a user.

2. **Constructor Injection:**
    - Generate a single public constructor that initializes all final fields.

3. **Validation Methods (Private):**
    - Implement `validate[Entity]Id(UUID id)`: Throws `BadRequestException` if null.
    - Implement `validate[Parent]Id(UUID parentId)`: Uses `repository.existsById()` and throws `BadRequestException` if the parent does not exist.

4. **Method Implementation Logic:**
    - **getById:** Must use `.map(Converter::toDTO).orElseThrow(() -> new NotFoundException(...))`.
    - **create:** - Validate parent existence first.
        - Use the `Entity.builder()` pattern.
        - Save via repository and return converted DTO.
    - **getAllBy[Parent]Id:** - Validate parent ID first.
        - Stream the repository result and map to DTO list.

## Controller Implementation Protocol

1. **Mapping:** Use `@RequestMapping("/api/v1/[resource-plural]")`.
2. **Constructor Injection:** Inject the Interface (not the Impl).
3. **Response Types:** - Use `ResponseEntity<T>` for all endpoints.
    - Return `HttpStatus.CREATED` (201) for POST.
    - Return `HttpStatus.NO_CONTENT` (204) for DELETE.
4. **Annotation Precision:** Every @PathVariable and @RequestParam must explicitly define its name in parentheses, e.g., @PathVariable("userId").
5. **Path Consistency:** Ensure the URI path variables match the method parameters 1:1.
6. **Validation:** Always include the @Valid annotation on @RequestBody parameters to trigger DTO bean validation.

## Code Style Requirements
- Use **Lombok** `@Builder` if present in the entity, otherwise use the manual Builder defined in the project.
- Use **Stream API** for all collection transformations.
- Ensure `@Service` and `@RestController` annotations are present.