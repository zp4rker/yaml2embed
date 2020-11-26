package com.zp4rker.yaml2embed

/**
 * @author zp4rker
 */
class YamlEmbed(data: Map<String, Any>) {

    val author: Map<String, Any>?
    val title: Map<String, Any>?
    val description: String?
    val footer: Map<String, Any>?
    val fields: List<Map<String, Any>>?
    val color: String?
    val thumbnail: String?
    val image: String?

    init {
        if (data.containsKey("author")) {
            val value = data["author"]
            if (value is Map<*, *>) {
                val tmpMap = mutableMapOf<String, Any>()
                if (value.containsKey("name")) tmpMap["name"] = value["name"]!!
                if (value.containsKey("iconUrl")) tmpMap["iconUrl"] = value["iconUrl"]!!
                if (value.containsKey("url")) tmpMap["url"] = value["url"]!!
                author = tmpMap
            } else author = null
        } else author = null

        if (data.containsKey("title")) {
            val value = data["title"]
            if (value is Map<*, *>) {
                val tmpMap = mutableMapOf<String, Any>()
                if (value.containsKey("text")) tmpMap["text"] = value["text"]!!
                if (value.containsKey("url")) tmpMap["url"] = value["url"]!!
                title = tmpMap
            } else title = null
        } else title = null

        description = if (data.containsKey("description")) data["description"].toString() else null

        if (data.containsKey("footer")) {
            val value = data["footer"]
            if (value is Map<*, *>) {
                val tmpMap = mutableMapOf<String, Any>()
                if (value.containsKey("text")) tmpMap["text"] = value["text"]!!
                if (value.containsKey("iconUrl")) tmpMap["iconUrl"] = value["iconUrl"]!!
                footer = tmpMap
            } else footer = null
        } else footer = null

        if (data.containsKey("fields")) {
            val list = data["fields"]
            if (list is List<*>) {
                val tmpList = mutableListOf<Map<String, Any>>()
                for (v in list) {
                    if (v is Map<*, *>) {
                        val tmpMap = mutableMapOf<String, Any>()
                        if (v.containsKey("name")) tmpMap["name"] = v["name"].toString()
                        if (v.containsKey("value")) tmpMap["value"] = v["value"].toString()
                        if (v.containsKey("inline")) tmpMap["inline"] = v["inline"].toString().toBoolean()
                        tmpList.add(tmpMap)
                    }
                }
                fields = tmpList
            } else fields = null
        } else fields = null

        color = if (data.containsKey("color")) data["color"].toString() else null

        thumbnail = if (data.containsKey("thumbnail")) data["thumbnail"].toString() else null

        image = if (data.containsKey("image")) data["image"].toString() else null
    }

}