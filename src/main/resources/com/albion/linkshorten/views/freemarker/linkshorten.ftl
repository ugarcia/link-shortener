<#-- @ftlvariable name="" type="com.albion.linkshorten.views.LinkShortenView" -->
<html>
    <head>
        <title>${linkShortenTemplate.title}</title>
        <link rel="stylesheet" type="text/css" href="/assets/css/shorten.css">
    </head>
    <body>
        <h1>${linkShortenTemplate.headline?html}</h1>
        <input type='link' class='link-input' placeholder="${linkShortenTemplate.placeholderText}">
        <button class='shorten-button'>${linkShortenTemplate.buttonText}</button>

        <div class="errors"></div>

        <div class='link-container'></div>

        <script type="text/javascript" src="/assets/js/jquery.min.js"></script>
        <script type="text/javascript" src="/assets/js/shorten.js"></script>
    </body>
</html>