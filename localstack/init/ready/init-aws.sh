#!/bin/bash

#initialisation script for localstack

# Create S3 bucket for testing
awslocal s3api create-bucket --bucket cemo-test-bucket

# Create SQS for testing
awslocal sqs create-queue --queue-name cemo-test-bucket --region eu-west-1
