package hh.jug.d_rendite;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class KontorenditeServiceTest {
    private AutoCloseable closeable;

    @InjectMocks
    private KontorenditeService kontorenditeService;

    @Mock
    private KontoDatenbank kontoDatenbank;

    @BeforeEach
    void initMocks() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeMocks() throws Exception {
        closeable.close();
    }

    @Test
    void testKontoRenditeService() {
        LocalDate mockedDate = LocalDate.of(2025, 5, 2);
        LocalDate firstDayOfYear = mockedDate.with(firstDayOfYear());

        when(kontoDatenbank.getKontostand("testnummer", firstDayOfYear)).thenReturn(new Kontostand("EUR", 2000));
        when(kontoDatenbank.getKontostand("testnummer", mockedDate)).thenReturn(new Kontostand("EUR", 2320));

        List<Transaktion> mockedTransaktionen = List.of(
                new Transaktion(Transaktion.Art.BUCHUNG, 500),
                new Transaktion(Transaktion.Art.ZINSEN, 40),
                new Transaktion(Transaktion.Art.BUCHUNG, -200),
                new Transaktion(Transaktion.Art.GEBUEHR, -20)
        );
        when(kontoDatenbank.getTransaktionen("testnummer", firstDayOfYear, mockedDate)).thenReturn(mockedTransaktionen);

        try (MockedStatic<LocalDate> topDateTimeUtilMock = Mockito.mockStatic(LocalDate.class, Mockito.CALLS_REAL_METHODS)) {
            topDateTimeUtilMock.when(() -> LocalDate.now()).thenReturn(mockedDate);
            Rendite rendite = kontorenditeService.calculateJahresrenditeThisYearSoFar("testnummer");

            assertEquals(20, rendite.absolute());
            assertEquals(0.01, rendite.relative());
        }
    }
}