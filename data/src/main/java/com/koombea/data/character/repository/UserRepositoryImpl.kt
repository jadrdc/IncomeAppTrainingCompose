package com.koombea.data.character.repository

import com.couchbase.lite.Expression
import com.koombea.couchbasewrapper.database.CouchbaseDatabase
import com.koombea.couchbasewrapper.database.CouchbaseDocument
import com.koombea.data.character.base.OperationResult
import com.koombea.data.character.datasource.entity.User

class UserRepositoryImpl(private val couchbaseDatabase: CouchbaseDatabase) : UserRepository {
    override suspend fun getUser(): OperationResult<User> {
        val user = couchbaseDatabase.fetch<User>("1")
        return if (user == null) {
            OperationResult.Error(Exception("Error"))
        } else {
            OperationResult.Success(user)
        }
    }

    override suspend fun saveUser() {
        if (couchbaseDatabase.fetch<User>("1") == null) {
            val user = User("Agustin Reinoso", "jadrdc@gmail.com")
            val document = CouchbaseDocument(id = user.id, attributes = user)
            couchbaseDatabase.save(document)
        }
    }

    override suspend fun updateUser(updateUser: User) {
        couchbaseDatabase.save(
            CouchbaseDocument(id = updateUser.id, attributes = updateUser)
        )
    }

    override suspend fun findUser(email: String, password: String): OperationResult<User> {
        return try {
            val expression =
                Expression.property("attributes.email").equalTo(Expression.string(email)).and(
                    Expression.property("attributes.password").equalTo(Expression.string(password)))
            val user = couchbaseDatabase.fetchAll<User>(expression).firstOrNull()
            if (user == null) {
                OperationResult.Error(Exception("Not found"))
            } else {
                OperationResult.Success(user)
            }
        } catch (ex: Exception) {
            OperationResult.Error(ex)
        }
    }

    override suspend fun saveUser(user: User): OperationResult<Boolean> {
        return try {
            val document = CouchbaseDocument(id = user.id, attributes = user)
            couchbaseDatabase.save(document)
            OperationResult.Success(true)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }
}