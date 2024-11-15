# Use the official MongoDB base image
FROM mongo:latest

# Expose the default MongoDB port
EXPOSE 27017

# (Optional) Add custom configurations or initialization scripts
COPY mongo-init.js /docker-entrypoint-initdb.d/

# Default command (already set in the official image)
CMD ["mongod"]