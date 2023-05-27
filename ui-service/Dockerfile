FROM node:16.15.1
WORKDIR /usr/src/app/ui-service
COPY package*.json ./
RUN npm install

EXPOSE 8080
CMD ["npm", "run", "serve"]