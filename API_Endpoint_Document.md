# Endpoints
## `getViolationsResponseByAddress` "/property/{address}"
#### This endpoint will query both the Violations and Scofflaws table and retrieve:
* List of Violations associated with the address
* Date of last violation
* Total violation count
* Scofflaw status
#### Then return it packaged in a `ViolationsResponse`.
### Request
- **Method**: `GET`
- **URL**: `/property/{address}`
- **Path Variable**: String `address` with '+' instead of regular spaces.
  - Example: `"123+w+grace+st"`, `"123+W+GRACE+ST"`
### Example Request
`curl -X GET "http://localhost:8080/property/5036+N+SHERIDAN+RD" `
### Response
* `200 OK` — success
* `404 NOT FOUND` — address is not in database
### Response Body
```
{
  "last_violation_date": string,
  "total_violation_count": int,
  "violation_list":[
    {
      "violation_date": date,
      "violation_code": string,
      "violation_description": string,
      "violation_inspector_comments": string,
      "violation_status": string
    },
    ...
  ],
  "scofflaw_status": boolean 
}
```

## `getScofflawsByDate` "/property/scofflaws/violations"
#### This endpoint will query the Scofflaw table and retrieve:
* List of Scofflaws that were listed on or after a given date.
#### Then return it packaged in a `ScofflawsResponse`.
### Request
- **Method**: `GET`
- **URL**: `/property/scofflaws/violations?since={date}`
- **Query Parameter**: String `date` in the format 'YYYY-MM-DD'.
    - Example: `2025-08-08`
### Example Request
`curl -X GET "http://localhost:8080/property/scofflaws/violations?since=2025-03-01" `
### Response
* `200 OK` — success
* `404 NOT FOUND` — no scofflaws found during or after given date
### Response Body
```
[
  {
    "address": string,
    "building_list_date": date
  }
  ...
]
```
## `createCommentWithAddress` "/property/{address}/comments"
#### This endpoint will insert a comment that is tied to an address.
### Request
- **Method**: `POST`
- **URL**: `/property/{address}/comments`
- **Path Variable**: String `address` with '+' instead of regular spaces.
  - Example: `"123+w+grace+st"`, `"123+W+GRACE+ST"`
### Request Body
```
{
  "comment": string,
  "author": string (15 character limit)
}
```
### Example Request
```
curl -X POST \
-H "Content-Type: application/json" \
-d '{"comment":"I have cockroaches! Won'\''t somebody help me?","author":"DesperateMan"}' \
"http://localhost:8080/property/5036+N+SHERIDAN+RD/comments"
```
### Response
* `201 CREATED` — success
### Response PayLoad Example
* `"Successfully created comment tied to 5036+N+SHERIDAN+RD."`

  
