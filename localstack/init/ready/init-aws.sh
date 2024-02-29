#!/bin/bash

#initialisation script for localstack

# Create SQS for testing
awslocal sqs create-queue --queue-name cemo-test-sqs --region eu-west-1
