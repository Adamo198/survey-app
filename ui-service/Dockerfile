FROM node:18.16.0
WORKDIR /usr/src/app/ui-service
COPY package*.json ./
RUN npm install

EXPOSE 8080
CMD ["npm", "run", "serve"]