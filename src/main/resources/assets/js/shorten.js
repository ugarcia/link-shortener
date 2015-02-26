(function() {

    // Add event to 'shorten' button.
    $(document).on('click', '.shorten-button', function(evt) {

        // TODO: Validate text field.

        $('.link-container').html("Waiting ...").fadeIn();
        $('.errors').html("").fadeOut();

        // Send original link to API.
        $.ajax({
            url: '/api/linkshorten/links',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                original: $('.link-input').val()
            }),
            error: function(res, status, err) {
                console.log(res,err);
                $('.errors').html(res.responseJSON.errors[0]).fadeIn();
                $('.link-container').html("Failed.");
            },
            success: function(data, status, res) {
                console.log(data);
                $('.link-container').html(window.location.origin + '/' + data.shortened);
            }
        });

    });

})();
