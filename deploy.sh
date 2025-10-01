#!/bin/bash

# Agify App Deployment Script
# This script helps deploy the application to various platforms

echo "🚀 Agify Age Estimator - Deployment Helper"
echo "=========================================="

# Check if we're in the right directory
if [ ! -f "pom.xml" ]; then
    echo "❌ Error: pom.xml not found. Please run this script from the project root."
    exit 1
fi

# Function to build the application
build_app() {
    echo "📦 Building application..."
    mvn clean package -DskipTests
    if [ $? -eq 0 ]; then
        echo "✅ Build successful!"
    else
        echo "❌ Build failed!"
        exit 1
    fi
}

# Function to test the application
test_app() {
    echo "🧪 Testing application..."
    mvn test
    if [ $? -eq 0 ]; then
        echo "✅ Tests passed!"
    else
        echo "❌ Tests failed!"
        exit 1
    fi
}

# Function to run locally
run_local() {
    echo "🏃 Running application locally..."
    mvn spring-boot:run
}

# Function to create Docker image
docker_build() {
    echo "🐳 Building Docker image..."
    docker build -t agify-app .
    if [ $? -eq 0 ]; then
        echo "✅ Docker image built successfully!"
        echo "To run: docker run -p 8080:8080 agify-app"
    else
        echo "❌ Docker build failed!"
        exit 1
    fi
}

# Function to show deployment options
show_deployment_options() {
    echo ""
    echo "🌐 Deployment Options:"
    echo "====================="
    echo ""
    echo "1. Railway (Recommended for Spring Boot)"
    echo "   - Go to https://railway.app"
    echo "   - Connect your GitHub repository"
    echo "   - Deploy automatically"
    echo ""
    echo "2. Heroku"
    echo "   - Install Heroku CLI"
    echo "   - Run: heroku create your-app-name"
    echo "   - Run: git push heroku main"
    echo ""
    echo "3. Google Cloud Run"
    echo "   - Build Docker image: docker build -t agify-app ."
    echo "   - Push to Google Container Registry"
    echo "   - Deploy to Cloud Run"
    echo ""
    echo "4. AWS Elastic Beanstalk"
    echo "   - Create application in AWS Console"
    echo "   - Upload JAR file or connect GitHub"
    echo ""
    echo "5. DigitalOcean App Platform"
    echo "   - Go to https://cloud.digitalocean.com/apps"
    echo "   - Connect GitHub repository"
    echo "   - Deploy automatically"
    echo ""
}

# Main menu
case "${1:-menu}" in
    "build")
        build_app
        ;;
    "test")
        test_app
        ;;
    "run")
        run_local
        ;;
    "docker")
        docker_build
        ;;
    "deploy")
        build_app
        test_app
        show_deployment_options
        ;;
    "menu"|*)
        echo ""
        echo "Usage: ./deploy.sh [command]"
        echo ""
        echo "Commands:"
        echo "  build    - Build the application"
        echo "  test     - Run tests"
        echo "  run      - Run locally"
        echo "  docker   - Build Docker image"
        echo "  deploy   - Build, test, and show deployment options"
        echo "  menu     - Show this menu (default)"
        echo ""
        ;;
esac
