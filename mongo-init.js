// db.createUser({
//     user: "appuser",
//     pwd: "apppassword",
//     roles: [{ role: "readWrite", db: "appdb" }]
// });
//
// db = db.getSiblingDB("appdb"); // Switch to the new database
// db.createCollection("exampleCollection");

db = db.getSiblingDB("MongoDown2Mark")

db.createCollection("Markdown", {
    validator: {
        $jsonSchema: {
            bsonType: "object",
            required: ["token", "title", "contentVersionPairs"],
            properties: {
                token: {
                    bsonType: "string",
                    description: "Token must be a string and is required"
                },
                title: {
                    bsonType: "string",
                    description: "Title must be a string and is required"
                },
                autoSave: {
                    bsonType: "array",
                    description: "Must be an array of content-version pairs and is required",
                    items: {
                        bsonType: "object",
                        required: ["content", "version"],
                        properties: {
                            content: {
                                bsonType: "string",
                                description: "Content must be a string"
                            },
                            version: {
                                bsonType: "string",
                                description: "Version must be a string representing the content version"
                            }
                        }
                    }
                }
            }
        }
    }
});
