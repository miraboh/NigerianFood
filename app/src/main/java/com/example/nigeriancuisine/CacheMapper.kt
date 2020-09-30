package com.example.nigeriancuisine

import javax.inject.Inject

class CacheMapper @Inject constructor(): EntityMapper<FoodCacheEntity, Food> {

    override fun mapFromEntity(entity: FoodCacheEntity): Food {
        return Food(
            id = entity.id,
            name = entity.name,
            pics = entity.pics,
            likes = entity.likes,
            about = entity.about,
            origin = entity.origin,
            foodClass = entity.foodClass,
            procedure = entity.procedure,
            ingredients = entity.ingredients
        )
    }

    override fun mapToEntity(domainModel: Food): FoodCacheEntity {
        return FoodCacheEntity(
            id = domainModel.id,
            name = domainModel.name,
            pics = domainModel.pics,
            likes = domainModel.likes,
            about = domainModel.about,
            origin = domainModel.origin,
            foodClass = domainModel.foodClass,
            procedure = domainModel.procedure,
            ingredients = domainModel.ingredients
        )
    }

    fun mapFromEntityList(entities: List<FoodCacheEntity>): List<Food>{
        return entities.map { mapFromEntity(it) }
    }
}











