# Conventions

The current development conventions which should be adhered to for additions and alterations to the library

## Model Conventions

### Java Class Names

Models for requests and responses use a suffix for the top-level representation. For Requests, this is `*Request`, for responses, `*Responses`. Classes used purely as sub-classes within other requests/responses do not have a suffix.

### Java Class Comparison

Model class instance equality will be based on the data contained within the instance.

### Optional JSON Field Representation

JSON fields which are optional are represented as "nullable" within the Java model.

### Unknown JSON Field Handling

Deserialization of responses will not fail due to additional unknown JSON fields in the response. Such fields will be ignored