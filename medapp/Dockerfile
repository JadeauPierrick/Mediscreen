FROM node:18.2.0
WORKDIR /app
ADD . .
EXPOSE 4200
 
RUN npm install
 
ENTRYPOINT ["npm", "start"]