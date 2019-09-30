package app.tuanluc.repository

import app.tuanluc.model.Chapter

/**
 * @author tuanluc on 21/09/2019
 */

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface ChapterRepository extends MongoRepository<Chapter, ObjectId> {

}
