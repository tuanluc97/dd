package app.tuanluc.repository

import app.tuanluc.model.Story
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * @author tuanluc on 22/09/2019
 */
interface StoryRepository extends MongoRepository<Story, ObjectId> {

    Optional<Story> findOneBySeoUrl(String SeoUrl)

}