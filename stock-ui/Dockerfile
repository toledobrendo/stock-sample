# BASE IMAGE with an alias #
FROM node:18.12.1-alpine3.16 as build
WORKDIR /app

RUN npm set timeout=100000

# Install Angular CLI to run Build #
RUN npm install -g @angular/cli

RUN apk update && apk upgrade && \
    apk add --no-cache bash git openssh
RUN git --version

COPY ./package.json .
RUN npm install --loglevel verbose
COPY . .
RUN ng build --configuration production

# BASE IMAGE with an alias #
FROM nginx as runtime

# Copy contents from the other container with alias "build" #
# onto the specified path in the current container#
COPY --from=build /app/dist/stock-ui /usr/share/nginx/html
COPY nginx.conf /etc/nginx/nginx.conf
EXPOSE 80
