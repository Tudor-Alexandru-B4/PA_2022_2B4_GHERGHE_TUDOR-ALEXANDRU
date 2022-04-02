<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width">
    <title>report</title>
  </head>
  <body>
    <h1>Report for Catalog:<h1>
    <ul>
        <#list itemCatalog as item>
          <li>Type: ${item.type}</li>
          <li>Id: ${item.id}</li>
          <li>Name: ${item.name}</li>
          <li>Location: ${item.location}</li>
          <br/><br/>
        </#list>
    </ul>
  </body>
</html>

