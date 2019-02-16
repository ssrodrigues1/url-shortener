package br.com.shortener.converters;

import br.com.shortener.domains.ShortUrl;
import br.com.shortener.gateways.http.json.response.ShortUrlResponseJson;
import org.springframework.stereotype.Component;

@Component
public class ShortUrlConverter {

  public static final String SHORT_CONTEXT = "short";

  public ShortUrlResponseJson convertToShortUrlResponseJson(String serverContextUrl, ShortUrl shortUrl) {
    return new ShortUrlResponseJson(String.format("%s/%s/%s", serverContextUrl, SHORT_CONTEXT, shortUrl.getId()));
  }
}
