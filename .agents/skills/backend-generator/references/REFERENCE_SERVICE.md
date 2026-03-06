# Reference Service Implementation (NoteServiceImpl)

This is the gold standard for Service implementation in this project.

```java
@Service
@RequiredArgsConstructor // Or manual constructor as per your skill rules
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final NoteConverter noteConverter;

    @Override
    @Transactional(readOnly = true)
    public NoteResponseDTO getNoteById(UUID noteId) {
        validateId(noteId);
        return noteRepository.findById(noteId)
                .map(noteConverter::toResponseDTO)
                .orElseThrow(() -> new NotFoundException("Note not found with ID: " + noteId));
    }

    @Override
    @Transactional
    public NoteResponseDTO createNote(NoteRequestDTO dto) {
        validateUserId(dto.getUserId());
        
        Note entity = Note.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .user(userRepository.getReferenceById(dto.getUserId()))
                .build();

        Note saved = noteRepository.save(entity);
        return noteConverter.toResponseDTO(saved);
    }

    // --- Private Validations ---
    private void validateId(UUID id) {
        if (id == null) throw new BadRequestException("The ID must not be null");
    }

    private void validateUserId(UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("User not found with ID: " + userId);
        }
    }
}