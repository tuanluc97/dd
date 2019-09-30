package app.tuanluc.model

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

/**
 * @author tuanluc on 22/09/2019
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Document(collection = "story")
class Story {
    ObjectId _id
    String name
    String seoUrl
    String description
    int status
    String authorId
    List<String> categoryIds
    List<String> tags
    int like
    int chapterNumber

    final static class STATUS {
        final static COMPLETE = 1
        final static CONTINUE = 2
        final static DROP = 3
    }

    String get_id() {
        return _id.toString()
    }
}
