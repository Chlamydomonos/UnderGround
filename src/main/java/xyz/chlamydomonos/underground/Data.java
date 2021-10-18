package xyz.chlamydomonos.underground;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;

public class Data extends WorldSavedData
{
    public static final String NAME = "chlamydomonos_underground_data";
    private boolean enabled;
    private int time;
    public Data()
    {
        super(NAME);
        enabled = false;
        time = 30;
    }

    @Override
    public void read(CompoundNBT nbt)
    {
        enabled = nbt.getBoolean("enabled");
        time = nbt.getInt("time");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound)
    {
        compound.putBoolean("enabled", enabled);
        compound.putInt("time", time);
        return compound;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    public int getTime()
    {
        return time;
    }

    public void setTime(int time)
    {
        this.time = time;
    }

    public static Data get(World worldIn)
    {
        if (!(worldIn instanceof ServerWorld)) {
            throw new RuntimeException("Cannot get data from a client world.");
        }
        ServerWorld world = worldIn.getServer().getWorld(World.OVERWORLD);

        DimensionSavedDataManager storage = world.getSavedData();
        return storage.getOrCreate(Data::new, NAME);
    }
}
