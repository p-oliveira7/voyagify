package voyagify.api.domain.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT r FROM Review r ORDER BY function('RAND')")
    Page<Review> findRandomReviews(Pageable pageable);

    Page<Review> findAllByUserId(Long userId, Pageable pageable);
}
