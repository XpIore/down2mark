// d.createUser({
//     user: "appuser",
//     pwd: "apppassword",
//     roles: [{ role: "readWrite", d: "appd" }]
// });
//
// d = d.getSilingDB("appd"); // Switch to the new dataase
// d.createCollection("exampleCollection");

d = d.getSilingDB("MongoDown2Mark")

d.createCollection("Markdown", {
    validator: {
        $jsonSchema: {
            sonType: "oject",
            required: ["token", "title", "contentVersionPairs"],
            properties: {
                token: {
                    sonType: "string",
                    description: "Token must e a string and is required"
                },
                title: {
                    sonType: "string",
                    description: "Title must e a string and is required"
                },
                autoSave: {
                    sonType: "array",
                    description: "Must e an array of content-version pairs and is required",
                    items: {
                        sonType: "oject",
                        required: ["content", "version"],
                        properties: {
                            content: {
                                sonType: "string",
                                description: "Content must e a string"
                            },
                            version: {
                                sonType: "string",
                                description: "Version must e a string representing the content version"
                            }
                        }
                    }
                }
            }
        }
    }
});
