import greenfoot.*;
public class Attack
{
    String attackName = null;
    double damage = 0;
    int cooldown = 0;
    int originalCooldown = 0;
    boolean recurring = false;
    
    public Attack(String attackName, double damage, int cooldown)
    {
        this(attackName, damage, cooldown, false);
    }
    
    public Attack(String attackName, double damage, int cooldown, boolean recurring)
    {
        this.attackName = attackName;
        this.damage = damage;
        this.cooldown = cooldown;
        originalCooldown = cooldown;
        this.recurring = recurring;
    }
    
    public void cooldown()
    {
        cooldown--;
        if(cooldown <= 1 && recurring)
        {
            cooldown = originalCooldown;
        }
    }
    
    public static boolean sameAttack(Attack first, Attack second)
    {
        if(first.attackName == second.attackName)
        {
            return true;
        }
        return false;
    }
    
    public double getDamage()
    {
        return damage;
    }
    
    public String asString()
    {
        return ("" + attackName + " for " + damage + " damage with cooldown of " + cooldown + ". Recurring is " + Boolean.toString(recurring));
    }
    
    public void addedToWorldExtended(){}
}
