package br.com.shortener.usecases;

import br.com.shortener.domains.collections.ShortUrl;
import br.com.shortener.exceptions.RecordNotFoundException;
import br.com.shortener.gateways.ShortUrlGateway;
import br.com.shortener.templates.Templates;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RetrieveShortUrlTest {

  @InjectMocks private RetrieveShortUrl retrieveShortUrl;

  @Mock private ShortUrlGateway shortUrlGateway;

  @BeforeClass
  public static void beforeClass() {
    FixtureFactoryLoader.loadTemplates(Templates.TEMPLATES_PACKAGE);
  }

  @Test
  public void retrieveWithFindShortUrl() {
    // GIVEN
    final String shortUrlId = "123456";
    final ShortUrl shortUrl = Fixture.from(ShortUrl.class).gimme(Templates.SHORT_URL);

    // WHEN
    when(shortUrlGateway.get(eq(shortUrlId))).thenReturn(Optional.of(shortUrl));
    ShortUrl returnedUrl = retrieveShortUrl.execute(shortUrlId);

    // THEN
    verify(shortUrlGateway, times(1)).get(eq(shortUrlId));
    assertThat(returnedUrl).isEqualTo(shortUrl);
  }

  @Test(expected = RecordNotFoundException.class)
  public void retrieveWithNotFindShortUrl() {
    // GIVEN
    final String shortUrlId = "123456";

    // WHEN
    when(shortUrlGateway.get(eq(shortUrlId))).thenReturn(Optional.empty());
    ShortUrl returnedUrl = retrieveShortUrl.execute(shortUrlId);

    // THEN
    verify(shortUrlGateway, times(1)).get(eq(shortUrlId));
  }
}
