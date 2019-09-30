package app.tuanluc.repository

import app.tuanluc.model.Category
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * @author tuanluc on 28/09/2019
 */
interface CategoryRepository extends MongoRepository<Category, ObjectId> {
}
