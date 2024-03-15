package shared.dto.request;

import java.net.URI;

public record RemoveLinkRequest(
    URI url
) {

}
