package pl.training.commons;

import java.util.List;

public record ResultPage<T>(List<T> elements, int totalPages) {
}
