package app.tuanluc.model

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

/**
 * @author tuanluc on 28/09/2019
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Document(collection = "category")
class Category {

    ObjectId _id
    String categoryId
    String description
    String title

    String get_id() {
        return _id.toString()
    }
}
