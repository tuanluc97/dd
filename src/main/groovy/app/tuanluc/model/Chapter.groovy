package app.tuanluc.model

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

/**
 * @author tuanluc on 21/09/2019
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Document(collection = "chapter")
class Chapter {
    ObjectId _id
    String storyId
    int chapterNumber
    String title
    String content

    String get_id() {
        return _id.toString()
    }
}

