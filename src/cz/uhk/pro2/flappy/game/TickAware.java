package cz.uhk.pro2.flappy.game;
/**
 * rozhrani pro objekty ktere potrebuji vedet kolik casu/ticku ubehlo od zacatku
 * @author vaverlu1
 *
 */
public interface TickAware {
	/**
	 * zmeni stav herni entity s ohledem na zmenu herniho casu
	 * @param ticksSinceStart cas/pocet ticku od zahajeni hry
	 */
	void tick(long ticksSinceStart);
}
