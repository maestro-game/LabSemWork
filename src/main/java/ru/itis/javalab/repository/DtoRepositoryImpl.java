package ru.itis.javalab.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.dto.contentsource.PreviewSourceDto;
import ru.itis.javalab.dto.message.RemoveMessageDto;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DtoRepositoryImpl implements DtoRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<PreviewSourceDto> previewSourceDtoRowMapper = (rs, rowNum) -> new PreviewSourceDto(
            rs.getString("id"),
            rs.getString("name"),
            rs.getString("lastMessageShortText"),
            rs.getTimestamp("lastMessageTimestamp")
    );

    private final RowMapper<RemoveMessageDto> removeMessageDtoRowMapper = (rs, rowNum) -> RemoveMessageDto.builder()
            .lastMessageShortText(rs.getString("text"))
            .lastMessageTimestamp(rs.getTimestamp("created"))
            .build();

    @Override
    public List<PreviewSourceDto> findAllPreviewSourceDtoByMember(String id) {
        return jdbcTemplate.query("select s.id as id, s.name as name, m.text as lastMessageShortText, m.created as lastMessageTimestamp " +
                "from content_source as s " +
                "         join user_source as u on (s.id = ? or u.user_id = ?) and u.source_id = s.id" +
                "         join (select distinct on (m.source_id) m.source_id, m.text, m.created, m.id from message as m order by m.source_id, m.id desc) as m on s.id = m.source_id " +
                "order by m.id desc;", previewSourceDtoRowMapper, id, id);
    }

    @Override
    public RemoveMessageDto findRemoveMessageDtoBySourceIdAndMessageId(String sourceId, Long messageId) {
        var result = jdbcTemplate.queryForObject("select text, created " +
                        "from message as m where source_id = ? order by id desc limit 1",
                removeMessageDtoRowMapper,
                sourceId);
        if (result == null) return null;
        result.setId(messageId);
        return result;
    }

    @Override
    public PreviewSourceDto findPreviewSourceDtoById(String id) {
        return jdbcTemplate.queryForObject("select s.id as id, s.name as name, m.text as lastMessageShortText, m.created as lastMessageTimestamp " +
                        "from content_source as s " +
                        "join (select m.source_id, m.text, m.created from message as m where m.source_id = ? order by m.id desc limit 1) as m on s.id = ?",
                previewSourceDtoRowMapper, id, id);
    }
}
